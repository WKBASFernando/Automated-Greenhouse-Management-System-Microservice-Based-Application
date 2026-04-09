package lk.ijse.Crop_Management_Service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Crop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cropId;

    private String commonName;
    private String scientificName;
    private String category; // e.g., Cereal, Legume, Vegetable
    private String cropImage; // Base64 or URL

    // This links the Crop to a specific Zone in your other service
    private Long assignedZoneId;
}