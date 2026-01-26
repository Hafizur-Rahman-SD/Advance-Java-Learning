package bd.edu.seu.shopnopuribackend.modules.match.api;

import bd.edu.seu.shopnopuribackend.modules.match.dto.MatchRes;
import bd.edu.seu.shopnopuribackend.modules.match.svc.MatchSvc;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/match")
@RequiredArgsConstructor
public class MatchCtl {

    private final MatchSvc svc;

    // GET /api/match?career=Software%20Engineer
    @GetMapping
    public MatchRes byCareer(@RequestParam String career) {
        return svc.byCareer(career);
    }
}
