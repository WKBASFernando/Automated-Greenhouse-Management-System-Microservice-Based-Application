package lk.ijse.Zone_Management_Service.service;

import lk.ijse.Zone_Management_Service.client.ExternalIoTClient;
import lk.ijse.Zone_Management_Service.entity.Zone;
import lk.ijse.Zone_Management_Service.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class ZoneService {
    @Autowired
    private ExternalIoTClient iotClient;

    @Autowired
    private ZoneRepository repository;

    public Zone createZone(Zone zone, String token) {
        if (zone.getMinTemp() >= zone.getMaxTemp()) {
            throw new RuntimeException("Validation Failed: Min Temp must be less than Max Temp");
        }

        Map<String, String> payload = Map.of("name", zone.getName());
        String bearerToken = token.startsWith("Bearer ") ? token : "Bearer " + token;

        try {
            Map<String, Object> response = iotClient.registerDevice(bearerToken, payload);
            String deviceId = (String) response.get("deviceId");
            zone.setDeviceId(deviceId);
        } catch (Exception e) {
            System.err.println("Failed to register device with IoT Provider: " + e.getMessage());
            zone.setDeviceId("MOCK-" + UUID.randomUUID().toString().substring(0, 8));
        }

        return repository.save(zone);
    }

    // FIXED: Return the Optional directly from the repository.
    // Do not throw the exception here; let the Controller handle the "Not Found" logic.
    public Optional<Zone> getZoneById(Long id) {
        return repository.findById(id);
    }

    // FIXED: Correctly unwrapping the Optional to update and save the Entity
    public Zone updateZone(Long id, Zone details) {
        return repository.findById(id).map(existingZone -> {
            if (details.getMinTemp() >= details.getMaxTemp()) {
                throw new RuntimeException("minTemp must be less than maxTemp");
            }
            existingZone.setMinTemp(details.getMinTemp());
            existingZone.setMaxTemp(details.getMaxTemp());
            existingZone.setMinHumidity(details.getMinHumidity());
            existingZone.setMaxHumidity(details.getMaxHumidity());
            return repository.save(existingZone);
        }).orElseThrow(() -> new RuntimeException("Zone not found with ID: " + id));
    }

    public void deleteZone(Long id) {
        repository.deleteById(id);
    }
}