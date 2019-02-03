package pl.projekt.tristar.ProjektSpringTristar20.services.vms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.projekt.tristar.ProjektSpringTristar20.domain.model.VmsEntity;
import pl.projekt.tristar.ProjektSpringTristar20.domain.repository.VmsRepository;
import pl.projekt.tristar.ProjektSpringTristar20.model.VmsLocationPOJO;
import pl.projekt.tristar.ProjektSpringTristar20.model.VmsPOJO;
import pl.projekt.tristar.ProjektSpringTristar20.model.VmsSinglePOJO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VmsService {

    @Autowired
    VmsRepository vmsRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void getVmsFromJson() {

        RestTemplate restTemplate = new RestTemplate();
        String vmsUrl = "http://api.zdiz.gdynia.pl/ri/rest/vms";

        VmsPOJO vmsPOJO = restTemplate.getForObject(vmsUrl, VmsPOJO.class);

        vmsPOJO.getVms().stream().map(this::map).forEach(vmsRepository::save);

    }

    public VmsEntity map(VmsSinglePOJO source) {

        return VmsEntity.builder()
                .lastUpdate(source.getLastUpdate())
                .lat(source.getLocation().getCoordinates().get(1))
                .lng(source.getLocation().getCoordinates().get(0))
                .vmsId(source.getId())
                .build();
    }

    public VmsSinglePOJO map(VmsEntity source) {
        List<String> coordinatesList = new ArrayList<>();
        coordinatesList.add(0,source.getLng());
        coordinatesList.add(1,source.getLat());
        return VmsSinglePOJO.builder()
                .id(source.getVmsId())
                .lastUpdate(source.getLastUpdate())
                .location(VmsLocationPOJO.builder()
                        .coordinates(coordinatesList)
                        .build())
                .build();
    }

    public List<VmsSinglePOJO> getAllVms() {

        return vmsRepository.findAll().stream().map(this::map).collect(Collectors.toList());
    }


    public List<VmsEntity> getAll(){


        return vmsRepository.findAll();

    }


}
