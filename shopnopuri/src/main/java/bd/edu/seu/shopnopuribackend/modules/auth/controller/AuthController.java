package bd.edu.seu.shopnopuribackend.modules.auth.controller;

import bd.edu.seu.shopnopuribackend.modules.auth.dto.AuthResponse;
import bd.edu.seu.shopnopuribackend.modules.auth.dto.LoginRequest;
import bd.edu.seu.shopnopuribackend.modules.auth.dto.RegisterRequest;
import bd.edu.seu.shopnopuribackend.modules.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;



//HTTP endpoint create for login reg
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody RegisterRequest req) {
        return authService.register(req);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest req) {
        return authService.login(req);
    }
}
