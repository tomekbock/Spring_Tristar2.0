package pl.projekt.tristar.ProjektSpringTristar20.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.projekt.tristar.ProjektSpringTristar20.domain.model.WeatherInfoEntity;

@Repository
public interface WeatherInfoRepository extends JpaRepository<WeatherInfoEntity, Long> {

    WeatherInfoEntity findFirstByWeatherStationIdOrderByDownloadTimeDesc(Long id);
}
