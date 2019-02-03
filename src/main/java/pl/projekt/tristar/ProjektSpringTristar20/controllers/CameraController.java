package pl.projekt.tristar.ProjektSpringTristar20.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.projekt.tristar.ProjektSpringTristar20.services.cameras.CamerasFromJson;
import pl.projekt.tristar.ProjektSpringTristar20.services.vms.VmsService;
import pl.projekt.tristar.ProjektSpringTristar20.services.weather_stations.WeatherInfoService;
import pl.projekt.tristar.ProjektSpringTristar20.services.weather_stations.WeatherStationsService;

import java.io.IOException;
import java.util.Date;


@Controller
public class CameraController {

    @Autowired
    CamerasFromJson camerasFromJson;
    @Autowired
    WeatherStationsService weatherStationsService;
    @Autowired
    VmsService vmsService;
    @Autowired
    WeatherInfoService weatherInfoService;


    @GetMapping("/")
    public String index(){
        return "index";
    }




    @GetMapping("/cameras")
    public String show(Model model) {
        model.addAttribute("serverTime", new Date());
        return "tristarCameras";
    }

    @GetMapping("/test")
    public String showTest(Model model) {
        model.addAttribute("serverTime", new Date());
        return "test";
    }









    @GetMapping("/cameras/{id}")
    public String showURL(@PathVariable(value = "id") Integer id) throws IOException {

        return "redirect:http://51.38.132.218" + camerasFromJson.getCameraInfo(id);

    }

//    @ModelAttribute()
//    public void addAttributes(Model model) {
//
//        List<CameraEntity> cameras = camerasFromJson.getCameras();
//        model.addAttribute("cameraName", cameras);
//
//    }
}
