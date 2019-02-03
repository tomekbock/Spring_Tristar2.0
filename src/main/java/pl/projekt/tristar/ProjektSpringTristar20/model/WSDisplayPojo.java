package pl.projekt.tristar.ProjektSpringTristar20.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WSDisplayPojo {


    public WSDisplayPojo(String name, String description, String category, double lat, double lng) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }



    private Long id;
    private String name;
    private Double lat;
    private Double lng;
}
