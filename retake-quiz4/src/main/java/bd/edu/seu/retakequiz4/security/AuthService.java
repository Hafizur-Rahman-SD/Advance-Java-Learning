package bd.edu.seu.retakequiz4.security;

import bd.edu.seu.retakequiz4.dto.LoginRequest;
import bd.edu.seu.retakequiz4.dto.LoginResponse;
import bd.edu.seu.retakequiz4.model.AppUser;
import bd.edu.seu.retakequiz4.repo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepo, PasswordEncoder encoder, JwtService jwtService) {
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.jwtService = jwtService;
    }

    public LoginResponse login(LoginRequest req) {
        AppUser u = userRepo.findByUsername(req.getUsername()).orElseThrow();

        if (!encoder.matches(req.getPassword(), u.getPasswordHash())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(u.getUsername(), u.getRoles());
        return new LoginResponse(token, u.getUsername(), u.getRoles());
    }
}
