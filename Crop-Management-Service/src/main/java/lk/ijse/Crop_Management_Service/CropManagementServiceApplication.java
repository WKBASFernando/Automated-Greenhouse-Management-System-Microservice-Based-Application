package lk.ijse.Crop_Management_Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CropManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CropManagementServiceApplication.class, args);
	}

}
