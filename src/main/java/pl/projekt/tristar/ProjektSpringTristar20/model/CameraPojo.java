package pl.projekt.tristar.ProjektSpringTristar20.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)

public class CameraPojo {

    private String name;

    private CameraLocationPojo location;

    private Integer id;


}
