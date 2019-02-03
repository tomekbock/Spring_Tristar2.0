package pl.projekt.tristar.ProjektSpringTristar20.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MapPointEntity {

    @Id
    private Integer id;
    private String name;
    private Double lat;
    private Double lng;


}
