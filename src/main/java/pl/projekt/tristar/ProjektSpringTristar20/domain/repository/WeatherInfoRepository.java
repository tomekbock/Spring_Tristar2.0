package pl.projekt.tristar.ProjektSpringTristar20.domain.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import pl.projekt.tristar.ProjektSpringTristar20.domain.model.WeatherInfoEntity;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WeatherInfoRepository extends JpaRepository<WeatherInfoEntity, Long> {


    List<WeatherInfoEntity> findByWeatherStationId(Long id, Pageable pageable);
    WeatherInfoEntity findFirstByWeatherStationIdOrderByDownloadTimeDesc(Long id);

    void deleteAllByDownloadTimeBefore(LocalDateTime date);

}
