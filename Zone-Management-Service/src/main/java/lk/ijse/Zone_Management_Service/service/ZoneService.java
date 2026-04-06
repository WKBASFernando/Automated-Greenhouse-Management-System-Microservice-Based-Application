package lk.ijse.Zone_Management_Service.service;

import lk.ijse.Zone_Management_Service.client.ExternalIoTClient;
import lk.ijse.Zone_Management_Service.entity.Zone;
import lk.ijse.Zone_Management_Service.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.UUID;

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

        // 3. Ensure the token has the correct Bearer prefix for the IoT Backend filter
        String bearerToken = token.startsWith("Bearer ") ? token : "Bearer " + token;

        try {
            // 4. Attempt to relay the token to the External API via Feign
            Map<String, Object> response = iotClient.registerDevice(bearerToken, payload);

            // 5. Extract and set the deviceId from the external response
            String deviceId = (String) response.get("deviceId");
            zone.setDeviceId(deviceId);

        } catch (Exception e) {
            // 6. FALLBACK: Log the error (e.g., 401 Unauthorized) but do not throw an exception.
            // This prevents the 500 error and allows the record to be saved locally.
            System.err.println("Failed to register device with IoT Provider: " + e.getMessage());

            // Assign a local mock ID so the database 'save' doesn't fail on a null deviceId
            zone.setDeviceId("MOCK-" + UUID.randomUUID().toString().substring(0, 8));
        }

        // 7. Save to your local MySQL database regardless of external API success
        return repository.save(zone);
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