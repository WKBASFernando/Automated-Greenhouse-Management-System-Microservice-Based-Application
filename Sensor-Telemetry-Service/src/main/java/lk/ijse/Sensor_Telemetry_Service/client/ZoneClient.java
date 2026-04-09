package lk.ijse.Sensor_Telemetry_Service.client;

import lk.ijse.Sensor_Telemetry_Service.dto.ZoneDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ZONE-MANAGEMENT-SERVICE")
public interface ZoneClient {
    @GetMapping("/api/zones/{id}") // Remove /v1/ if it's not in the original controller
    ZoneDTO getZoneDetails(@PathVariable("id") String id);
}