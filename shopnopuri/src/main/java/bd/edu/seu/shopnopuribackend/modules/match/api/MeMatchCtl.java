package bd.edu.seu.shopnopuribackend.modules.match.api;

import bd.edu.seu.shopnopuribackend.modules.match.dto.MatchRes;
import bd.edu.seu.shopnopuribackend.modules.match.svc.MatchSvc;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/me")
@RequiredArgsConstructor
public class MeMatchCtl {

    private final MatchSvc svc;

    // GET /api/me/match
    @GetMapping("/match")
    public MatchRes myMatch(Principal principal) {
        return svc.byEmail(principal.getName());
    }
}
