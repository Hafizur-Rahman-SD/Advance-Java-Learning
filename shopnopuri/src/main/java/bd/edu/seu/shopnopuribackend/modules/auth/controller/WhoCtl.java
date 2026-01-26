package bd.edu.seu.shopnopuribackend.modules.auth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WhoCtl {

    @GetMapping("/api/who")
    public String who(Authentication auth) {
        return auth == null ? "NO_AUTH" : auth.getName() + " " + auth.getAuthorities();
    }
}
