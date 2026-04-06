package lk.ijse.Zone_Management_Service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(
        name = "external-iot-service",
        url = "${iot.external.base-url:http://localhost:8090/api}"
)
public interface ExternalIoTClient {

    @PostMapping("/devices")
    Map<String, Object> registerDevice(
            @RequestHeader("Authorization") String token,
            @RequestBody Map<String, String> payload
    );
}
