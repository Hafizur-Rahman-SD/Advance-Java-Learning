package bd.edu.seu.shopnopuribackend.modules.matchmaker;


import bd.edu.seu.shopnopuribackend.modules.matchmaker.MatchmakerResponse;
import bd.edu.seu.shopnopuribackend.modules.matchmaker.MatchmakerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/matchmaker")
@RequiredArgsConstructor
public class MatchmakerController {

    private final MatchmakerService matchmakerService;

    // GET /api/matchmaker/career?career=Software%20Engineer
    @GetMapping("/career")
    public MatchmakerResponse career(@RequestParam String career) {
        return matchmakerService.suggestByCareer(career);
    }
}

