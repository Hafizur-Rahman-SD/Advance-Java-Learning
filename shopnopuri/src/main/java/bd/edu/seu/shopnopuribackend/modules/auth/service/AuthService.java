package bd.edu.seu.shopnopuribackend.modules.auth.service;

import bd.edu.seu.shopnopuribackend.modules.auth.dto.AuthResponse;
import bd.edu.seu.shopnopuribackend.modules.auth.dto.LoginRequest;
import bd.edu.seu.shopnopuribackend.modules.auth.dto.RegisterRequest;
import bd.edu.seu.shopnopuribackend.modules.user.entity.Role;
import bd.edu.seu.shopnopuribackend.modules.user.entity.User;
import bd.edu.seu.shopnopuribackend.modules.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


//Business logic handle reh and login system

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

        return new AuthResponse(saved.getId(), saved.getEmail(), saved.getRole().name(), "Registered successfully");
    }

    public AuthResponse login(LoginRequest req) {
        User u = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        boolean ok = passwordEncoder.matches(req.getPassword(), u.getPasswordHash());
        if (!ok) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        return new AuthResponse(u.getId(), u.getEmail(), u.getRole().name(), "Login successful");
    }
}
