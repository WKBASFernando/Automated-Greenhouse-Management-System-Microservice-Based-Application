package lk.ijse.Sensor_Telemetry_Service.client;

import lk.ijse.Sensor_Telemetry_Service.dto.AutomationRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "AUTOMATION-CONTROL-SERVICE")
public interface AutomationClient {

    @PostMapping("/api/v1/automation/trigger")
    String sendAutomationCommand(@RequestBody AutomationRequestDTO request);
}