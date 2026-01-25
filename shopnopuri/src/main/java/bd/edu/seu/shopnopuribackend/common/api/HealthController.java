package bd.edu.seu.shopnopuribackend.common.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public String health() {
        return "OK This app is Running by Hafizur Rahman";
    }
}
