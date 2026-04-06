package lk.ijse.Zone_Management_Service.repository;

import lk.ijse.Zone_Management_Service.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {
}