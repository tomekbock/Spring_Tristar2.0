package pl.projekt.tristar.ProjektSpringTristar20.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.projekt.tristar.ProjektSpringTristar20.domain.model.CameraEntity;
import pl.projekt.tristar.ProjektSpringTristar20.domain.model.MapPointEntity;

@Repository
public interface MapPointRepository extends JpaRepository<MapPointEntity,Integer> {


}
