package pl.projekt.tristar.ProjektSpringTristar20.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeatherInfoPOJO {
    private String visibility;

    private String strenghtWind;

    private String surfaceTemperature;

    private String foundationTemperature;

    private String dewPoint;

    private String measureTime;

    private String chemicalConcentration;

    private String airTemperature;

    private String waterIceThickness;

    private int id;

    private int windDirection;

    private String windSpeed;

    private int weatherStationId;
}
