package pl.projekt.tristar.ProjektSpringTristar20.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.projekt.tristar.ProjektSpringTristar20.domain.model.WeatherStationEntity;

import javax.persistence.Id;

@Repository
public interface WeatherStationRepository extends JpaRepository<WeatherStationEntity, Long> {


    WeatherStationEntity findByStationId(int stationId);
    WeatherStationEntity findById(Id id);



}
