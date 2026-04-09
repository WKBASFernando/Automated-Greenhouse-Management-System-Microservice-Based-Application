package lk.ijse.Sensor_Telemetry_Service.service;

import lk.ijse.Sensor_Telemetry_Service.client.ZoneClient;
import lk.ijse.Sensor_Telemetry_Service.dto.SensorDataDTO;
import lk.ijse.Sensor_Telemetry_Service.dto.ZoneDTO;
import lk.ijse.Sensor_Telemetry_Service.entity.SensorData;
import lk.ijse.Sensor_Telemetry_Service.repository.SensorDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SensorDataService {

    private final ZoneClient zoneClient;
    private final SensorDataRepository repository;

    public String processReadings(SensorDataDTO dto) {
        // 1. Fetch thresholds from Zone Service (Inter-service communication)
        ZoneDTO zone = zoneClient.getZoneDetails(dto.getZoneId());

        // 2. Determine environment status
        String status = calculateStatus(dto, zone);

        // 3. Persist the reading to the history database
        SensorData entity = new SensorData();
        entity.setZoneId(dto.getZoneId());
        entity.setTemperature(dto.getTemperature());
        entity.setHumidity(dto.getHumidity());
        entity.setTimestamp(LocalDateTime.now());

        repository.save(entity);

        return "Reading processed for " + zone.getName() + ". Environment is " + status;
    }

    private String calculateStatus(SensorDataDTO reading, ZoneDTO limits) {
        if (reading.getTemperature() > limits.getMaxTemp()) return "CRITICAL (High Temp)";
        if (reading.getTemperature() < limits.getMinTemp()) return "CRITICAL (Low Temp)";
        if (reading.getHumidity() > limits.getMaxHumidity()) return "WARNING (High Humidity)";
        if (reading.getHumidity() < limits.getMinHumidity()) return "WARNING (Low Humidity)";
        return "STABLE";
    }
}