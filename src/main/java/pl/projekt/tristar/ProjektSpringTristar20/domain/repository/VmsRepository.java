package pl.projekt.tristar.ProjektSpringTristar20.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.projekt.tristar.ProjektSpringTristar20.domain.model.VmsEntity;

@Repository
public interface VmsRepository extends JpaRepository<VmsEntity, Integer> {

    VmsEntity findById(int id);

}
