package pl.projekt.tristar.ProjektSpringTristar20.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WSDisplayPojo {


    public WSDisplayPojo(Long id, String name, double lat, double lng) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }



    private Long id;
    private String name;
    private Double lat;
    private Double lng;
    private LocalDateTime downloadTime;

    private String visibility;

    private String strenghtWind;

    private String surfaceTemperature;

    private String foundationTemperature;

    private String dewPoint;

    private String measureTime;

    private String chemicalConcentration;

    private String airTemperature;

    private String waterIceThickness;

    private String windDirection;

    private String windSpeed;








}
