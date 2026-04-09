package lk.ijse.Crop_Management_Service.service;

import lk.ijse.Crop_Management_Service.client.ZoneClient;
import lk.ijse.Crop_Management_Service.entity.Crop;
import lk.ijse.Crop_Management_Service.repository.CropRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CropService {

    @Autowired
    private CropRepository cropRepository;

    @Autowired
    private ZoneClient zoneClient; // This is your OpenFeign Client

    public Crop saveCrop(Crop crop) {
        ResponseEntity<?> response = zoneClient.getZoneById(crop.getAssignedZoneId());

        if (response.getStatusCode().is2xxSuccessful()) {
            // Success: The zone exists!
            return cropRepository.save(crop);
        } else if (response.getStatusCode().value() == 404) {
            // Handle specifically if the zone was not found
            throw new RuntimeException("Zone ID " + crop.getAssignedZoneId() + " not found in the system.");
        } else {
            throw new RuntimeException("Failed to verify zone. Status: " + response.getStatusCode());
        }
    }

    public Optional<Crop> getCropById(Long id) {
        return cropRepository.findById(id);
    }
}
