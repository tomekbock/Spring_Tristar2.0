package pl.projekt.tristar.ProjektSpringTristar20.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.projekt.tristar.ProjektSpringTristar20.domain.model.WSDisplayEntity;

import java.util.List;

@Repository
public interface WSDisplayRepository extends JpaRepository<WSDisplayEntity, Long> {
    @Query(value = "Select * from WEATHER_STATION_ENTITY we\n" +
            "join WEATHER_INFO_ENTITY wi\n" +
            "on we.STATION_ID = wi.WEATHER_STATION_ID \n" +
            "where we.STATION_ID = :station_id\n" +
            "order by wi.DOWNLOAD_TIME desc", nativeQuery = true)
    WSDisplayEntity findWSDisplayEntityByStationId(@Param("station_id") Long stationId);
}
