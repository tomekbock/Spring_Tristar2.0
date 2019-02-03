package pl.projekt.tristar.ProjektSpringTristar20.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class MapPoint {

    public MapPoint(String name, String description, String category, double lat, double lng) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.lat = lat;
        this.lng = lng;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String category;
    private Double lat;
    private Double lng;
    private String link;


}
