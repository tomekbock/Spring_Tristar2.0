package pl.projekt.tristar.ProjektSpringTristar20.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.projekt.tristar.ProjektSpringTristar20.domain.model.MapPointEntity;
import pl.projekt.tristar.ProjektSpringTristar20.domain.model.VmsEntity;
import pl.projekt.tristar.ProjektSpringTristar20.domain.model.VmsPointEntity;


public interface VmsPointRepository extends JpaRepository<VmsPointEntity,Long> {


}
