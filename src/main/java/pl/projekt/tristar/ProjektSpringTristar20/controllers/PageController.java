package pl.projekt.tristar.ProjektSpringTristar20.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PageController {



    @RequestMapping("/CamerasMap")
    public String homePage()

    {

        return "camerasMap";
    }



    @RequestMapping("/VmsMap")
    public String homePage2()

    {

        return "vmsMap";
    }

    @RequestMapping("/WSMap")
    public String WeatherStationSite()

    {
        return "WSMap";
    }













}
