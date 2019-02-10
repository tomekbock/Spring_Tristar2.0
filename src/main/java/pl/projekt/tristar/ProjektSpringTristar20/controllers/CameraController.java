package pl.projekt.tristar.ProjektSpringTristar20.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import pl.projekt.tristar.ProjektSpringTristar20.services.cameras.CamerasService;

import java.io.IOException;
import java.util.Date;


@Controller
public class CameraController {

    @Autowired
    private CamerasService camerasService;

    @GetMapping("/")
    public String index() {
        return "index";
    }


    @GetMapping("/camera")
    public String show(Model model) {
        model.addAttribute("serverTime", new Date());
        return "tristarCameras";
    }


    @GetMapping("/cameras/{id}")
    public String showURL(@PathVariable(value = "id") Integer id) throws IOException {

        return "redirect:http://51.38.132.218" + camerasService.getCameraInfo(id);

    }

    @ModelAttribute
    public void addAttributes(Model model) {

        model.addAttribute("CamerasList", camerasService.getAllCameras());
    }



}
