package pl.projekt.tristar.ProjektSpringTristar20.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.projekt.tristar.ProjektSpringTristar20.domain.model.MapPointEntity;


public interface MapPointRepository extends JpaRepository<MapPointEntity,Integer> {


}
