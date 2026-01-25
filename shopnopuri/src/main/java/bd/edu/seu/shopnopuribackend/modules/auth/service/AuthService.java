package bd.edu.seu.shopnopuribackend.modules.auth.service;

import bd.edu.seu.shopnopuribackend.modules.auth.dto.AuthResponse;
import bd.edu.seu.shopnopuribackend.modules.auth.dto.LoginRequest;
import bd.edu.seu.shopnopuribackend.modules.auth.dto.RegisterRequest;
import bd.edu.seu.shopnopuribackend.modules.auth.security.JwtService;
import bd.edu.seu.shopnopuribackend.modules.user.entity.Role;
import bd.edu.seu.shopnopuribackend.modules.user.entity.User;
import bd.edu.seu.shopnopuribackend.modules.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponse register(RegisterRequest req) {
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        Role role = Role.valueOf(req.getRole().toUpperCase());
        if (role == Role.ADMIN) {
            throw new IllegalArgumentException("Admin registration not allowed");
        }

        User u = new User();
        u.setEmail(req.getEmail());
        u.setPasswordHash(passwordEncoder.encode(req.getPassword()));
        u.setRole(role);

        User saved = userRepository.save(u);

        String token = jwtService.generateToken(
                saved.getEmail(),
                Map.of("uid", saved.getId(), "role", saved.getRole().name())
        );

        return new AuthResponse(
                saved.getId(),
                saved.getEmail(),
                saved.getRole().name(),
                "Registered successfully",
                token
        );
    }

    public AuthResponse login(LoginRequest req) {
        User u = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        if (!passwordEncoder.matches(req.getPassword(), u.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        String token = jwtService.generateToken(
                u.getEmail(),
                Map.of("uid", u.getId(), "role", u.getRole().name())
        );

        return new AuthResponse(
                u.getId(),
                u.getEmail(),
                u.getRole().name(),
                "Login successful",
                token
        );
    }
}
