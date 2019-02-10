package pl.projekt.tristar.ProjektSpringTristar20.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.projekt.tristar.ProjektSpringTristar20.domain.model.CameraEntity;
import pl.projekt.tristar.ProjektSpringTristar20.model.CameraDisplayPojo;

import java.util.List;

@Repository
public interface CameraRepository extends JpaRepository<CameraEntity, Long> {







}
