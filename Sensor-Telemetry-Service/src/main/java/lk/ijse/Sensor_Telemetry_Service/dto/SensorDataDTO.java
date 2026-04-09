package lk.ijse.Sensor_Telemetry_Service.dto;

import lombok.Data;

@Data
public class SensorDataDTO {
    private String zoneId;
    private Double temperature;
    private Double humidity;
}
