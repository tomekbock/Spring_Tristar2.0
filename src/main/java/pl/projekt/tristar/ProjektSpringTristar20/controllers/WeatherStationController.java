package pl.projekt.tristar.ProjektSpringTristar20.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import pl.projekt.tristar.ProjektSpringTristar20.domain.model.WeatherInfoEntity;
import pl.projekt.tristar.ProjektSpringTristar20.domain.repository.WeatherStationRepository;
import pl.projekt.tristar.ProjektSpringTristar20.services.weather_stations.WeatherInfoService;
import pl.projekt.tristar.ProjektSpringTristar20.services.weather_stations.WeatherStationsService;

@Controller
public class WeatherStationController {

    @Autowired
    private WeatherInfoService weatherInfoService;
    @Autowired
    private WeatherStationsService weatherStationsService;

    @GetMapping("/weather/stations")
    public String weatherStations(Model model){
        model.addAttribute("weatherStations", weatherStationsService.getAllWeatherStations());

        return "weatherStations";
    }

    @GetMapping("/weather/info")
    public String weatherInfo(@RequestParam(value = "id") Long id, Model model){

        model.addAttribute("weatherStation",weatherStationsService.getWeatherStationById(id));
        model.addAttribute("weatherInfo", weatherInfoService.getWeatherInfoForCurrentStation(id));

        return "weatherInfo";
    }

    @GetMapping(path = "/history/{id}")
    public String history(@PathVariable("id") Long id, @RequestParam(value = "limit", defaultValue = "1", required = false) int limit,@RequestParam(value = "direction", required = false) String dir , Model model) {
        Sort.Direction direction;
        if ("asc".equalsIgnoreCase(dir)) {
            direction = Sort.Direction.ASC;
        } else {
            direction = Sort.Direction.DESC;
        }
        model.addAttribute("history", weatherInfoService.getHistory(id, limit, direction));
        return "history";
    }
}
