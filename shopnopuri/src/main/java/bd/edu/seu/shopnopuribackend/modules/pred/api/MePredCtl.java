package bd.edu.seu.shopnopuribackend.modules.pred.api;

import bd.edu.seu.shopnopuribackend.modules.match.dto.MatchRes;
import bd.edu.seu.shopnopuribackend.modules.match.svc.MatchSvc;
import bd.edu.seu.shopnopuribackend.modules.pred.dto.PredReq;
import bd.edu.seu.shopnopuribackend.modules.pred.dto.PredRes;
import bd.edu.seu.shopnopuribackend.modules.pred.svc.PredSvc;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/me")
@RequiredArgsConstructor
public class MePredCtl {

    private final MatchSvc matchSvc;
    private final PredSvc predSvc;

    // POST /api/me/pred
    // Body: {"mock":70,"extra":5}
    @PostMapping("/pred")
    public PredRes myPred(@Valid @RequestBody MePredReq body, Principal principal) {

        MatchRes mr = matchSvc.byEmail(principal.getName());
        if (mr.getItems() == null || mr.getItems().isEmpty()) {
            throw new IllegalArgumentException("No matchmaker suggestions found. Run career test + add university/dept data first.");
        }

        var first = mr.getItems().get(0);

        PredReq req = PredReq.builder()
                .uniId(first.getUniId())
                .deptId(first.getDeptId())
                .mock(body.getMock())
                .extra(body.getExtra())
                .build();

        return predSvc.calcFor(principal.getName(), req);
    }

    // small request DTO only for /api/me/pred
    @Getter
    @Setter
    public static class MePredReq {
        private Integer mock;   // 0..100
        private Integer extra;  // 0..10
    }
}
