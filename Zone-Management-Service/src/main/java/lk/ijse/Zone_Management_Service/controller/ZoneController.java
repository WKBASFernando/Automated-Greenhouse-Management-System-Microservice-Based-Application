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

    @PostMapping
    public ResponseEntity<Zone> createZone(
            @RequestBody Zone zone,
            @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok().body(zoneService.createZone(zone, token));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Zone> getZone(@PathVariable Long id, @RequestBody Zone zoneDetails) {
        return ResponseEntity.ok(zoneService.updateZone(id, zoneDetails));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Zone> updateZone(@PathVariable Long id, @RequestBody Zone zoneDetails) {
        return ResponseEntity.ok(zoneService.updateZone(id, zoneDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Zone> deleteZone(@PathVariable Long id) {
        zoneService.deleteZone(id);
        return ResponseEntity.ok().build();
    }
}
