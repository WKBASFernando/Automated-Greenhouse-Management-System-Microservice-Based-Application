package lk.ijse.Sensor_Telemetry_Service.dto;

import lombok.Data;

@Data
public class AutomationRequestDTO {
    private Long zoneId;
    private String deviceType;
    private String command; // START or STOP
    private String reason;
}
