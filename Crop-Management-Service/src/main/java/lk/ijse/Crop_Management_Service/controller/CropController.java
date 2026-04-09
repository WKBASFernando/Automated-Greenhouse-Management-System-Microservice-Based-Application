package lk.ijse.Crop_Management_Service.controller;

import lk.ijse.Crop_Management_Service.entity.Crop;
import lk.ijse.Crop_Management_Service.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/crops")
public class CropController {

    @Autowired
    private CropService cropService;

    @PostMapping
    public ResponseEntity<Crop> saveCrop(@RequestBody Crop crop) {
        return ResponseEntity.ok(cropService.saveCrop(crop));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Crop> getCrop(@PathVariable Long id) {
        return cropService.getCropById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
