package pl.projekt.tristar.ProjektSpringTristar20.services.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.projekt.tristar.ProjektSpringTristar20.domain.model.CameraEntity;
import pl.projekt.tristar.ProjektSpringTristar20.domain.model.MapPointEntity;
import pl.projekt.tristar.ProjektSpringTristar20.domain.repository.CameraRepository;
import pl.projekt.tristar.ProjektSpringTristar20.domain.repository.MapPointRepository;
import pl.projekt.tristar.ProjektSpringTristar20.model.MapPoint;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapService {

    @Autowired
    private MapPointRepository mapPointRepository;
    @Autowired
    private CameraRepository cameraRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void addPoint() {


        mapPointRepository.saveAll(cameraRepository.findAll().stream().map(this::maper).collect(Collectors.toList()));


    }


    public MapPointEntity maper(CameraEntity cameraEntity) {
        if (cameraEntity==null) {
            return null;
        }
        MapPointEntity mapPointEntity = new MapPointEntity();
        mapPointEntity.setId(cameraEntity.getCameraId());
        mapPointEntity.setLat(Double.parseDouble(cameraEntity.getLat()));
        mapPointEntity.setLng(Double.parseDouble(cameraEntity.getLng()));
        mapPointEntity.setName(cameraEntity.getName());

        return mapPointEntity;

    }


}




