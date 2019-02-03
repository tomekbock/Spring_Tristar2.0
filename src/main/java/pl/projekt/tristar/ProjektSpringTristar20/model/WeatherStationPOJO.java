package pl.projekt.tristar.ProjektSpringTristar20.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherStationPOJO {
    private int id;
    private String street;
    private WeatherStationLocationPOJO location;
}
