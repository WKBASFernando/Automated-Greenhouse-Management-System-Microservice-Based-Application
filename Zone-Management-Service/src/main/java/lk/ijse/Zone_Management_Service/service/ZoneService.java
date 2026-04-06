package lk.ijse.Zone_Management_Service.service;

import lk.ijse.Zone_Management_Service.client.ExternalIoTClient;
import lk.ijse.Zone_Management_Service.entity.Zone;
import lk.ijse.Zone_Management_Service.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ZoneService {
    @Autowired
    private ZoneRepository repository;
    @Autowired private ExternalIoTClient iotClient;

    public Zone createZone(Zone zone, String bearerToken) {
        // Business Logic: Validate thresholds
        if (zone.getMinTemp() >= zone.getMaxTemp()) {
            throw new IllegalArgumentException("minTemp must be less than maxTemp");
        }

        // External Integration: Register device [cite: 113, 115]
        Map<String, String> payload = Map.of(
                "name", zone.getName(),
                "zoneId", zone.getName()
        );

        Map<String, Object> response = iotClient.registerDevice(bearerToken, payload);
        zone.setDeviceId((String) response.get("deviceId")); // Store returned ID

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
