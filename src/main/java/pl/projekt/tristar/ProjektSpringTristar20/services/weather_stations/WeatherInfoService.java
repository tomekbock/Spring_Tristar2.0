package pl.projekt.tristar.ProjektSpringTristar20.services.weather_stations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.projekt.tristar.ProjektSpringTristar20.domain.model.WeatherInfoEntity;
import pl.projekt.tristar.ProjektSpringTristar20.domain.model.WeatherStationEntity;
import pl.projekt.tristar.ProjektSpringTristar20.domain.repository.WeatherInfoRepository;
import pl.projekt.tristar.ProjektSpringTristar20.domain.repository.WeatherStationRepository;
import pl.projekt.tristar.ProjektSpringTristar20.model.WeatherInfoPOJO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class WeatherInfoService {
    @Autowired
    private WeatherStationRepository weatherStationRepository;
    @Autowired
    private WeatherInfoRepository weatherInfoRepository;

    public WeatherInfoPOJO getWeatherInfoFromStation(int id) {
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

    @Scheduled(initialDelay = 5000, fixedDelay = 30000)
    public void getInfoForAllStations() {

        weatherStationRepository.findAll()
                .stream()
                .map(WeatherStationEntity::getStationId)
                .map(this::getWeatherInfoFromStation)
                .filter(Objects::nonNull)
                .map(this::map)
                .forEach(weatherInfoRepository::save);
    }

    public WeatherInfoPOJO getWeatherInfoForCurrentStation(int id) {
        return map(weatherInfoRepository.findFirstByWeatherStationIdOrderByDownloadTimeDesc(id));
    }


}
