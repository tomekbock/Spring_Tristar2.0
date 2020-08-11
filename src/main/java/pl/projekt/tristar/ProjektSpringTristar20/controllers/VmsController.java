package pl.projekt.tristar.ProjektSpringTristar20.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.projekt.tristar.ProjektSpringTristar20.services.vms.VmsInfoService;

import java.io.IOException;

@Controller
public class VmsController {

    @Autowired
    private VmsInfoService vmsInfoService;



    @GetMapping("/vms/{id}")
    public String showURL(@PathVariable(value = "id") Integer id) throws IOException {

        return "redirect:http://api.zdiz.gdynia.pl" + vmsInfoService.getVmsInfofromVms(id).getImageUrl();


    }

}