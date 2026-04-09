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
    private Long id;
    private Long zoneId;
    private String deviceType;
    private String actionStatus;
    private String triggerReason;
    private LocalDateTime timestamp;
}
