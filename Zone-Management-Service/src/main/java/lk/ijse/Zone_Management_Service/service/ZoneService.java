package lk.ijse.Zone_Management_Service.service;

import lk.ijse.Zone_Management_Service.client.ExternalIoTClient;
import lk.ijse.Zone_Management_Service.client.IoTAuthClient;
import lk.ijse.Zone_Management_Service.entity.Zone;
import lk.ijse.Zone_Management_Service.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

@Service
public class ZoneService {
    @Autowired
    private ExternalIoTClient iotClient;

    @Autowired
    private ZoneRepository repository;

    public Zone createZone(Zone zone, String token) {
        // 1. Validation Logic
        if (zone.getMinTemp() >= zone.getMaxTemp()) {
            throw new RuntimeException("Validation Failed: Min Temp must be less than Max Temp");
        }

        // 2. Prepare payload for the external IoT Provider
        Map<String, String> payload = Map.of("name", zone.getName());

        try {
            // 3. Relay the token to the External API via Feign
            Map<String, Object> response = iotClient.registerDevice(token, payload);

            // 4. Extract and set the deviceId from the external response
            String deviceId = (String) response.get("deviceId");
            zone.setDeviceId(deviceId);

            // 5. Save to your local MySQL database
            return repository.save(zone);

        } catch (Exception e) {
            throw new RuntimeException("Failed to register device with External IoT Provider: " + e.getMessage());
        }
    }

    public Zone getZoneById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Zone not found"));
    }

    public Zone updateZone(Long id, Zone details) {
        Zone zone = getZoneById(id);
        if (details.getMinTemp() >= details.getMaxTemp()) {
            throw new RuntimeException("minTemp must be less than maxTemp");
        }
        zone.setMinTemp(details.getMinTemp());
        zone.setMaxTemp(details.getMaxTemp());
        zone.setMinHumidity(details.getMinHumidity());
        zone.setMaxHumidity(details.getMaxHumidity());
        return repository.save(zone);
    }

    public void deleteZone(Long id) {
        repository.deleteById(id);
    }
}
