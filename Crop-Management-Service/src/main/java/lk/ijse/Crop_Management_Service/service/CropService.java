package lk.ijse.Crop_Management_Service.service;

import lk.ijse.Crop_Management_Service.client.ZoneClient;
import lk.ijse.Crop_Management_Service.dto.ZoneDTO;
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
    private ZoneClient zoneClient;

    public Crop saveCrop(Crop crop) {
        try {
            ResponseEntity<ZoneDTO> response = zoneClient.getZoneById(crop.getAssignedZoneId());

            if (response.getStatusCode().is2xxSuccessful()) {
                return cropRepository.save(crop);
            }
        } catch (feign.FeignException.NotFound e) {
            throw new RuntimeException("Zone ID " + crop.getAssignedZoneId() + " not found in the system.");
        } catch (Exception e) {
            throw new RuntimeException("Zone service is currently unavailable.");
        }
        return null;
    }

    public Optional<Crop> getCropById(Long id) {
        return cropRepository.findById(id);
    }
}
