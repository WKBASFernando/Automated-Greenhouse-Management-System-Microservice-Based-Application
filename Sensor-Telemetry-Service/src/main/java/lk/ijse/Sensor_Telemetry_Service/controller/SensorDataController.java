package lk.ijse.Sensor_Telemetry_Service.controller;


import lk.ijse.Sensor_Telemetry_Service.dto.SensorDataDTO;
import lk.ijse.Sensor_Telemetry_Service.service.SensorDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/telemetry")
@RequiredArgsConstructor
public class SensorDataController {

    private final SensorDataService sensorDataService;

    @PostMapping("/report")
    public ResponseEntity<String> receiveSensorData(@RequestBody SensorDataDTO dataDTO) {
        try {
            String result = sensorDataService.processReadings(dataDTO);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Error: " + e.getMessage());
        }
    }
}