package lk.ijse.Automation_Control_Service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class AutomationUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long unitId;
    private Long zoneId;
    private String equipmentType; // e.g., "IRRIGATION", "VENTILATION"
    private String status;        // "ON", "OFF", "STANDBY"
    private LocalDateTime lastTriggered;
}
