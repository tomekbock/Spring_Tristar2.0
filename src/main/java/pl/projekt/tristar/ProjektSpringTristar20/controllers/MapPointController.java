package pl.projekt.tristar.ProjektSpringTristar20.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.projekt.tristar.ProjektSpringTristar20.domain.model.MapPointEntity;
import pl.projekt.tristar.ProjektSpringTristar20.domain.model.VmsEntity;
import pl.projekt.tristar.ProjektSpringTristar20.domain.repository.MapPointRepository;
import pl.projekt.tristar.ProjektSpringTristar20.services.vms.VmsService;

import java.util.List;


@RestController
public class MapPointController {


    @Autowired
    private MapPointRepository mapPointRepository;
    @Autowired
    private VmsService vmsService;

    @RequestMapping("/get")
    public Iterable<MapPointEntity> list() {
        return mapPointRepository.findAll();
    }

    @RequestMapping("/getVms")
    public List<VmsEntity> listVms() {
       return vmsService.getAll();

    }


//    @RequestMapping("/get/{id}")
//    public MapPoint getById(@PathVariable(value = "id") long id){
//        return mapPointRepository.findOne(id);
//    }



}
