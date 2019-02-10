package pl.projekt.tristar.ProjektSpringTristar20.services.weather_stations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.projekt.tristar.ProjektSpringTristar20.domain.model.WeatherStationEntity;
import pl.projekt.tristar.ProjektSpringTristar20.domain.repository.WeatherStationRepository;
import pl.projekt.tristar.ProjektSpringTristar20.model.WSDisplayPojo;
import pl.projekt.tristar.ProjektSpringTristar20.model.WeatherStationLocationPOJO;
import pl.projekt.tristar.ProjektSpringTristar20.model.WeatherStationPOJO;
import pl.projekt.tristar.ProjektSpringTristar20.model.WeatherStationsPOJO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherStationsService {

    @Autowired
    private WeatherStationRepository weatherStationRepository;




    @EventListener(ApplicationReadyEvent.class)
    public void getfromJson() {
        weatherStationRepository.deleteAll();
        RestTemplate restTemplate = new RestTemplate();
        String weatherStationsUrl = "http://api.zdiz.gdynia.pl/ri/rest/weather_stations";

        WeatherStationsPOJO weatherStationsPOJO = restTemplate
                .getForObject(weatherStationsUrl, WeatherStationsPOJO.class);

        weatherStationsPOJO.getWeatherStations().stream().map(this::map).forEach(weatherStationRepository::save);

    }
    public WSDisplayPojo mapToDisplay(WeatherStationEntity source) {
        if (source == null) {
            return null;
        }
        return WSDisplayPojo.builder()
                .id(source.getStationId())
                .lat(source.getLat())
                .lng(source.getLng())
                .name(source.getStreet())
                .build();


    }

    public WeatherStationEntity map(WeatherStationPOJO source) {
        if (source == null) {
            return null;
        }
        return WeatherStationEntity.builder()
                .lat(source.getLocation().getCoordinates().get(1))
                .lng(source.getLocation().getCoordinates().get(0))
                .stationId(source.getId())
                .street(source.getStreet()).build();
    }

    public WeatherStationPOJO map(WeatherStationEntity source) {
        if (source == null) {
            return null;
        }
        List<Double> coordinatesList = new ArrayList<>();
        coordinatesList.add(0,source.getLng());
        coordinatesList.add(1,source.getLat());
        return WeatherStationPOJO.builder()
                .id(source.getStationId())
                .street(source.getStreet())
                .location(WeatherStationLocationPOJO.builder()
                        .coordinates(coordinatesList)
                        .build())
                .build();
    }

    public WeatherStationPOJO getWeatherStationById(Long id) {
        return map(weatherStationRepository.findByStationId(id));
    }


    public List<WSDisplayPojo> getAllWeatherStations() {

        return weatherStationRepository.findAll().stream().map(this::mapToDisplay).collect(Collectors.toList());
    }




}
