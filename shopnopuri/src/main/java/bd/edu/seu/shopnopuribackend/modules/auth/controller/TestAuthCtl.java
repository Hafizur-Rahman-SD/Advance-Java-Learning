package bd.edu.seu.shopnopuribackend.modules.auth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class TestAuthCtl {

    @GetMapping("/whoami")
    public String whoami(Authentication auth) {
        return auth == null ? "NO_AUTH" : (auth.getName() + " " + auth.getAuthorities());
    }
}
