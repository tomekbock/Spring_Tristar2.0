package pl.projekt.tristar.ProjektSpringTristar20.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.projekt.tristar.ProjektSpringTristar20.domain.repository.MapPointRepository;
import pl.projekt.tristar.ProjektSpringTristar20.model.CameraDisplayPojo;
import pl.projekt.tristar.ProjektSpringTristar20.model.VmsDisplayPojo;
import pl.projekt.tristar.ProjektSpringTristar20.model.WSDisplayPojo;
import pl.projekt.tristar.ProjektSpringTristar20.services.cameras.CamerasService;
import pl.projekt.tristar.ProjektSpringTristar20.services.vms.VmsService;
import pl.projekt.tristar.ProjektSpringTristar20.services.weather_stations.WeatherStationsService;

import java.util.List;


@RestController
public class MapPointController {



    @Autowired
    private VmsService vmsService;
    @Autowired
    private WeatherStationsService weatherStationsService;
    @Autowired
    private CamerasService camerasService;


    @RequestMapping("/cameras")
    public List<CameraDisplayPojo> list() {
        return camerasService.getAllCameras();
    }


    @RequestMapping("/ws")
    public List<WSDisplayPojo> listWS() {
        return weatherStationsService.getAllWeatherStations();

    }

    @RequestMapping("/vms")
    public List<VmsDisplayPojo> listVms() {
        return vmsService.getAllVmsToDisplay();

    }


}
