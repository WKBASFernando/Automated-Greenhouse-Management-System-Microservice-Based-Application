package lk.ijse.Automation_Control_Service.repository;

import lk.ijse.Automation_Control_Service.entity.AutomationUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutomationRepository extends JpaRepository<AutomationUnit, Long> {
}