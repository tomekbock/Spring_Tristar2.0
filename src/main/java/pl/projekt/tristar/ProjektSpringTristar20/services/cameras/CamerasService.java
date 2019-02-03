package pl.projekt.tristar.ProjektSpringTristar20.services.cameras;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.projekt.tristar.ProjektSpringTristar20.domain.model.CameraEntity;
import pl.projekt.tristar.ProjektSpringTristar20.domain.repository.CameraRepository;
import pl.projekt.tristar.ProjektSpringTristar20.model.CameraInfoPojo;
import pl.projekt.tristar.ProjektSpringTristar20.model.CameraLocationPojo;
import pl.projekt.tristar.ProjektSpringTristar20.model.CameraPojo;
import pl.projekt.tristar.ProjektSpringTristar20.model.CamerasPojo;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CamerasService {

    @Autowired
    private CameraRepository cameraRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void getCameras() {
        RestTemplate restTemplate = new RestTemplate();
        String camerasUrl = "http://api.zdiz.gdynia.pl/ri/rest/cameras";

        CamerasPojo camerasPojo = restTemplate
                .getForObject(camerasUrl, CamerasPojo.class);

        camerasPojo.getCameras().stream().map(this::map).forEach(cameraRepository::save);


    }

    public CameraEntity map(CameraPojo source) {
        if (source==null) {
            return null;
        }
        return CameraEntity.builder()
                .cameraId(source.getId())
                .lat(source.getLocation().getCoordinates().get(1))
                .lng(source.getLocation().getCoordinates().get(0))
                .name(source.getName()).build();
    }

    public CameraPojo map(CameraEntity source) {
        List<String> coordinatesList = new ArrayList<>();
        coordinatesList.add(0,source.getLng());
        coordinatesList.add(1,source.getLat());
        return CameraPojo.builder()
                .id(source.getCameraId())
                .name(source.getName())
                .location(CameraLocationPojo.builder()
                        .coordinates(coordinatesList)
                        .build())
                .build();
    }

    public String getCameraInfo(Integer cameraID) throws IOException {

        RestTemplate restTemplate = new RestTemplate();
        String camerasUrl = "http://51.38.132.218/ri/rest/camera_image_data?cameraId=" + cameraID;

        String json = restTemplate.getForObject(camerasUrl, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        List<CameraInfoPojo> list = objectMapper.readValue(json, objectMapper.getTypeFactory()
                .constructCollectionType(List.class, CameraInfoPojo.class));


        return list.get(0).getImageUrl();


    }


    public List<CameraPojo> getCamerasList() {

        return cameraRepository.findAll().stream().filter(Objects::nonNull).map(this::map).collect(Collectors.toList());


    }


}

