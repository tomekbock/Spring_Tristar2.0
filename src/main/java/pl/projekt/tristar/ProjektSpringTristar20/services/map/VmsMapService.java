package pl.projekt.tristar.ProjektSpringTristar20.services.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.projekt.tristar.ProjektSpringTristar20.domain.model.CameraEntity;
import pl.projekt.tristar.ProjektSpringTristar20.domain.model.VmsEntity;
import pl.projekt.tristar.ProjektSpringTristar20.domain.model.VmsPointEntity;
import pl.projekt.tristar.ProjektSpringTristar20.domain.repository.CameraRepository;
import pl.projekt.tristar.ProjektSpringTristar20.domain.repository.MapPointRepository;
import pl.projekt.tristar.ProjektSpringTristar20.domain.repository.VmsPointRepository;
import pl.projekt.tristar.ProjektSpringTristar20.domain.repository.VmsRepository;

import java.util.stream.Collectors;

@Service
public class VmsMapService {

    @Autowired
    private VmsRepository vmsRepository;

    @Autowired
    private VmsPointRepository vmsPointRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void addPoint() {

        vmsPointRepository.saveAll(vmsRepository.findAll().stream().map(this::maper).collect(Collectors.toList()));

    }


    public VmsPointEntity maper(VmsEntity vmsEntity) {
        VmsPointEntity vmsPointEntity = new VmsPointEntity();
        vmsPointEntity.setId(vmsEntity.getVmsId());
        vmsPointEntity.setLat(Double.parseDouble(vmsEntity.getLat()));
        vmsPointEntity.setLng(Double.parseDouble(vmsEntity.getLng()));


        return vmsPointEntity;

    }


}




