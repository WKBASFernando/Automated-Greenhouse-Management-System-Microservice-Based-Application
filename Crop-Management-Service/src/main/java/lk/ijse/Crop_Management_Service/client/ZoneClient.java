package lk.ijse.Crop_Management_Service.client;

import lk.ijse.Crop_Management_Service.dto.ZoneDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ZONE-MANAGEMENT-SERVICE")
public interface ZoneClient {
    @GetMapping("/api/zones/{id}")
    Object getZoneDetails(@PathVariable("id") Long id);

    @GetMapping("/api/zones/{id}")
    ResponseEntity<ZoneDTO> getZoneById(@PathVariable("id") Long id);
}
