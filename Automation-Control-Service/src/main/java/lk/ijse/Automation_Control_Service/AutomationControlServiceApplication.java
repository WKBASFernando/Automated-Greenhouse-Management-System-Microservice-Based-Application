package lk.ijse.Automation_Control_Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AutomationControlServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomationControlServiceApplication.class, args);
	}

}
