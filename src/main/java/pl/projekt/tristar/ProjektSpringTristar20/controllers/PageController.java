package pl.projekt.tristar.ProjektSpringTristar20.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.projekt.tristar.ProjektSpringTristar20.services.map.MapService;


@Controller
public class PageController {


    @Autowired
    MapService mapService;





    @RequestMapping("/CamerasMap")
    public String homePage()

    {
//       mapService.displayPoint();

        return "testMap";
    }



    @RequestMapping("/VmsMap")
    public String homePage2()

    {
        return "testVms";
    }

    @RequestMapping("/WSMap")
    public String WeatherStationSite()

    {
        return "WSMap";
    }









}
