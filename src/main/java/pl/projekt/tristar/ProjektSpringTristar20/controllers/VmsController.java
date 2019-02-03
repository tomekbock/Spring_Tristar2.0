package pl.projekt.tristar.ProjektSpringTristar20.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import pl.projekt.tristar.ProjektSpringTristar20.model.VmsInfoPojo;
import pl.projekt.tristar.ProjektSpringTristar20.services.vms.VmsInfoService;
import pl.projekt.tristar.ProjektSpringTristar20.services.vms.VmsService;

import java.io.IOException;

@Controller
public class VmsController {

    @Autowired
    VmsInfoService vmsInfoService;
    @Autowired
    VmsService vmsService;


    @GetMapping("/vms")
    public String vms(Model model) {
        model.addAttribute("vms", vmsService.getAllVms());
        return "vms";
    }

    @GetMapping("/vms/{id}")
    public String showURL(@PathVariable(value = "id") Integer id) throws IOException {

        return "redirect:http://51.38.132.218" + vmsInfoService.getVmsInfofromVms(id).getImageUrl();


    }

}