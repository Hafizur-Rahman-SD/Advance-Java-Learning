package bd.edu.seu.jwtsecurity.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DemoController {

    @GetMapping("/user/hello")
    public String userHello() {
        return "Hello User (authenticated)";
    }

    @GetMapping("/admin/hello")
    public String adminHello() {
        return "Hello Admin (ADMIN role)";
    }
}
