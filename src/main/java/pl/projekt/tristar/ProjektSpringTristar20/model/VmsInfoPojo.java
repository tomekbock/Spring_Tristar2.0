package pl.projekt.tristar.ProjektSpringTristar20.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VmsInfoPojo {
    private String insertTime;

    private String contentUrl;

    private String vmsId;

    private String imageUrl;

    private int id;
}
