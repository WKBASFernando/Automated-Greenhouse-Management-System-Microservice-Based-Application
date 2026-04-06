package lk.ijse.Zone_Management_Service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "iot-auth-client", url = "${iot.external.auth-url}")
public interface IoTAuthClient {

    @PostMapping
    Map<String, String> getToken(@RequestBody Map<String, String> credentials);
}
