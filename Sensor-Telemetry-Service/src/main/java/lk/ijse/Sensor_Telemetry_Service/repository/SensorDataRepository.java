package lk.ijse.Sensor_Telemetry_Service.repository;

import lk.ijse.Sensor_Telemetry_Service.entity.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, Long> {

    List<SensorData> findByZoneId(String zoneId);

    List<SensorData> findTop10ByZoneIdOrderByTimestampDesc(String zoneId);
}
