package pl.projekt.tristar.ProjektSpringTristar20.services.weather_stations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import pl.projekt.tristar.ProjektSpringTristar20.domain.model.WSDisplayEntity;
import pl.projekt.tristar.ProjektSpringTristar20.domain.model.WeatherInfoEntity;
import pl.projekt.tristar.ProjektSpringTristar20.domain.model.WeatherStationEntity;
import pl.projekt.tristar.ProjektSpringTristar20.domain.repository.WSDisplayRepository;
import pl.projekt.tristar.ProjektSpringTristar20.domain.repository.WeatherInfoRepository;
import pl.projekt.tristar.ProjektSpringTristar20.domain.repository.WeatherStationRepository;
import pl.projekt.tristar.ProjektSpringTristar20.model.WSDisplayPojo;
import pl.projekt.tristar.ProjektSpringTristar20.model.WeatherInfoPOJO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class WeatherInfoService {
    @Autowired
    private WeatherStationRepository weatherStationRepository;
    @Autowired
    private WeatherInfoRepository weatherInfoRepository;
    @Autowired
    private WSDisplayRepository wsDisplayRepository;

    public WeatherInfoPOJO getWeatherInfoFromStation(Long id) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String weatherStationUrl = "http://51.38.132.218/ri/rest/weather_station_data?weatherStationId=" + id;


            String json = restTemplate.getForObject(weatherStationUrl, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            List<WeatherInfoPOJO> list = objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, WeatherInfoPOJO.class));
            if (list.isEmpty() || list == null) {
                return null;
            }

            return list.get(0);
        } catch (Exception e) {
            return null;
        }


    }

    public WeatherInfoEntity map(WeatherInfoPOJO source) {
        if (source == null) {
            return null;
        }
        return WeatherInfoEntity.builder()
                .airTemperature(source.getAirTemperature())
                .chemicalConcentration(source.getChemicalConcentration())
                .dewPoint(source.getDewPoint())
                .downloadTime(LocalDateTime.now())
                .foundationTemperature(source.getFoundationTemperature())
                .measureTime(source.getMeasureTime())
                .stationId(source.getId())
                .strenghtWind(source.getStrenghtWind())
                .surfaceTemperature(source.getSurfaceTemperature())
                .visibility(source.getVisibility())
                .waterIceThickness(source.getWaterIceThickness())
                .windDirection(source.getWindDirection())
                .weatherStationId(source.getWeatherStationId()).windSpeed(source.getWindSpeed()).build();
    }

    public WeatherInfoPOJO map(WeatherInfoEntity source) {
        if (source == null) {
            return null;
        }
        return WeatherInfoPOJO.builder().airTemperature(source.getAirTemperature())
                .chemicalConcentration(source.getChemicalConcentration())
                .dewPoint(source.getDewPoint())
                .foundationTemperature(source.getFoundationTemperature())
                .measureTime(source.getMeasureTime())
                .id(source.getStationId())
                .strenghtWind(source.getStrenghtWind())
                .surfaceTemperature(source.getSurfaceTemperature())
                .visibility(source.getVisibility())
                .waterIceThickness(source.getWaterIceThickness())
                .windDirection(source.getWindDirection())
                .weatherStationId(source.getWeatherStationId()).windSpeed(source.getWindSpeed()).build();
    }
    public WSDisplayPojo map(WSDisplayEntity source) {
        if (source == null) {
            return null;
        }
        String windDirection = "N";
        int windDir = source.getWindDirection();
        if (windDir > 338 || windDir < 22) {
            windDirection = "N";
        } else if (windDir > 23 && windDir < 67) {
            windDirection = "NE";
        } else if (windDir > 68 && windDir < 112) {
            windDirection = "E";
        }else if (windDir > 113 && windDir < 157) {
            windDirection = "SE";
        }else if (windDir > 158 && windDir < 192) {
            windDirection = "S";
        }else if (windDir > 193 && windDir < 247) {
            windDirection = "SW";
        }else if (windDir > 248 && windDir < 292) {
            windDirection = "W";
        }else if (windDir > 293 && windDir < 337) {
            windDirection = "NW";
        }
        return WSDisplayPojo.builder()
                .id(source.getStationId())
                .lat(source.getLat())
                .lng(source.getLng())
                .name(source.getStreet())
                .airTemperature(source.getAirTemperature())
                .downloadTime(source.getDownloadTime())
                .chemicalConcentration(source.getChemicalConcentration())
                .dewPoint(source.getDewPoint())
                .foundationTemperature(source.getFoundationTemperature())
                .measureTime(source.getMeasureTime())
                .strenghtWind(source.getStrenghtWind())
                .surfaceTemperature(source.getSurfaceTemperature())
                .visibility(source.getVisibility())
                .waterIceThickness(source.getWaterIceThickness())
                .windDirection(windDirection)
                .windSpeed(source.getWindSpeed())
                .build();
    }

    @Scheduled(initialDelay = 5000, fixedDelay = 10000)
    public void getInfoForAllStations() {

        weatherStationRepository.findAll()
                .stream()
                .map(WeatherStationEntity::getStationId)
                .map(this::getWeatherInfoFromStation)
                .filter(Objects::nonNull)
                .map(this::map)
                .forEach(weatherInfoRepository::save);
    }

    public WeatherInfoPOJO getWeatherInfoForCurrentStation(Long id) {
        return map(weatherInfoRepository.findFirstByWeatherStationIdOrderByDownloadTimeDesc(id));
    }

    public List<WeatherInfoPOJO> getHistory(Long id, int limit, Sort.Direction direction) {
        PageRequest pageable = PageRequest.of(0, limit, direction, "downloadTime");
        return weatherInfoRepository.findByWeatherStationId(id,pageable).stream().map(this::map).collect(Collectors.toList());

    }
    public List<WSDisplayPojo> getAllWSDisplays(){
        List<WSDisplayEntity> wsDisplayEntityList = new ArrayList<>();
        for (WeatherStationEntity ws: weatherStationRepository.findAll()) {
            Long stationId = ws.getStationId();
            if (wsDisplayRepository.findWSDisplayEntityByStationId(stationId) != null) {
                wsDisplayEntityList.add(wsDisplayRepository.findWSDisplayEntityByStationId(stationId));
            }
        }
        return wsDisplayEntityList.stream().map(this::map).collect(Collectors.toList());
    }

    @Transactional
    @Scheduled(cron = "0 00 13 * * *")
    public void deleteOldInfo() {
        weatherInfoRepository.deleteAllByDownloadTimeBefore(LocalDateTime.now().minusDays(5));
    }



}
