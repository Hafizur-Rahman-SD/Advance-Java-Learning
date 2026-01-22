package bd.edu.seu.retakequiz4.controller;

import bd.edu.seu.retakequiz4.dto.LoginRequest;
import bd.edu.seu.retakequiz4.dto.LoginResponse;
import bd.edu.seu.retakequiz4.security.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
