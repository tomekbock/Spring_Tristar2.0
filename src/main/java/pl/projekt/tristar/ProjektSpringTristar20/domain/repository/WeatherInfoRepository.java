package pl.projekt.tristar.ProjektSpringTristar20.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.projekt.tristar.ProjektSpringTristar20.domain.model.WeatherInfoEntity;

public interface WeatherInfoRepository extends JpaRepository<WeatherInfoEntity, Long> {

    WeatherInfoEntity findFirstByWeatherStationIdOrderByDownloadTimeDesc(int id);
}
