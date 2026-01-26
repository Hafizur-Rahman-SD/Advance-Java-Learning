package bd.edu.seu.shopnopuribackend.modules.pred.api;

import bd.edu.seu.shopnopuribackend.modules.pred.dto.PredReq;
import bd.edu.seu.shopnopuribackend.modules.pred.dto.PredRes;
import bd.edu.seu.shopnopuribackend.modules.pred.svc.PredSvc;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/pred")
@RequiredArgsConstructor
public class PredCtl {

    private final PredSvc svc;

    @PostMapping
    public PredRes calc(@Valid @RequestBody PredReq req, Principal principal) {
        return svc.calcFor(principal.getName(), req);
    }
}
