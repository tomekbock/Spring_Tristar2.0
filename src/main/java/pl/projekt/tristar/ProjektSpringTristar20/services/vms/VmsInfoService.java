package pl.projekt.tristar.ProjektSpringTristar20.services.vms;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.projekt.tristar.ProjektSpringTristar20.model.VmsInfoPojo;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class VmsInfoService {

    public VmsInfoPojo getVmsInfofromVms(int id) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String vmsInfoUrl = "http://api.zdiz.gdynia.pl/ri/rest/vms_message_data?vmsId=" + id;


        String json = restTemplate.getForObject(vmsInfoUrl, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        List<VmsInfoPojo> list = objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, VmsInfoPojo.class));
        if (list.isEmpty()||list==null) {
            return null;
        }

        return list.get(0);


    }
}
