package pl.projekt.tristar.ProjektSpringTristar20.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Builder
public class WeatherInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

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

    private Long stationId;

    private int windDirection;

    private String windSpeed;

    private Long weatherStationId;
}
