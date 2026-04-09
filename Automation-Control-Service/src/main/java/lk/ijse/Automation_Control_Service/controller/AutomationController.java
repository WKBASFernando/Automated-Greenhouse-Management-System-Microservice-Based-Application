package lk.ijse.Automation_Control_Service.controller;

import lk.ijse.Automation_Control_Service.dto.AutomationRequestDTO;
import lk.ijse.Automation_Control_Service.service.AutomationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/automation")
public class AutomationController {

    @Autowired
    private AutomationService automationService;

    @PostMapping("/trigger")
    public ResponseEntity<String> triggerAutomation(@RequestBody AutomationRequestDTO request) {
        String result = automationService.processAutomation(request);
        return ResponseEntity.ok(result);
    }
}