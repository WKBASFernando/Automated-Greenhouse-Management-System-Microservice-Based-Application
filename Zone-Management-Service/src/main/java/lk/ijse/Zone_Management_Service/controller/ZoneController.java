package lk.ijse.Zone_Management_Service.controller;

import lk.ijse.Zone_Management_Service.entity.Zone;
import lk.ijse.Zone_Management_Service.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/zones")
public class ZoneController {

    @Autowired
    private ZoneService zoneService;

    // 1. GET a specific zone - This is the one the Telemetry Service calls
    @GetMapping("/{id}")
    public ResponseEntity<Zone> getZoneById(@PathVariable("id") Long id) {
        return zoneService.getZoneById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 2. CREATE a new zone
    @PostMapping
    public ResponseEntity<Zone> createZone(@RequestBody Zone zone, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(zoneService.createZone(zone, token));
    }

    // 3. UPDATE an existing zone - This should be @PutMapping, not @GetMapping!
    @PutMapping("/{id}")
    public ResponseEntity<Zone> updateZone(@PathVariable("id") Long id, @RequestBody Zone details) {
        try {
            return ResponseEntity.ok(zoneService.updateZone(id, details));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 4. DELETE a zone
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteZone(@PathVariable("id") Long id) {
        zoneService.deleteZone(id);
        return ResponseEntity.noContent().build();
    }
}