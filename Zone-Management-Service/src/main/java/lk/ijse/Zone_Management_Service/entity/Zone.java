package lk.ijse.Zone_Management_Service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double minTemp;
    private Double maxTemp;
    private Double minHumidity;
    private Double maxHumidity;
    private String deviceId; // From External IoT Service
}
