package pl.projekt.tristar.ProjektSpringTristar20.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherStationsPOJO {
    @JsonProperty("weatherStations")
    private List<WeatherStationPOJO> weatherStations;
}
