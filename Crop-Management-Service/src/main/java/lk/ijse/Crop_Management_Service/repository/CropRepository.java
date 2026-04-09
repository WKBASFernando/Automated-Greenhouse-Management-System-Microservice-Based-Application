package lk.ijse.Crop_Management_Service.repository;

import lk.ijse.Crop_Management_Service.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CropRepository extends JpaRepository<Crop, Long> {
    List<Crop> findByAssignedZoneId(Long zoneId);
}
