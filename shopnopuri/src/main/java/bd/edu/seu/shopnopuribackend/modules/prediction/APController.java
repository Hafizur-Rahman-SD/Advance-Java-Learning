package bd.edu.seu.shopnopuribackend.modules.prediction;

import bd.edu.seu.shopnopuribackend.modules.prediction.dto.APChanceRequest;
import bd.edu.seu.shopnopuribackend.modules.prediction.dto.APResponse;
import bd.edu.seu.shopnopuribackend.modules.prediction.service.APService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/prediction/chance")
@RequiredArgsConstructor
public class APController {

    private final APService apService;

    @PostMapping
    public APResponse calculate(@Valid @RequestBody APChanceRequest req, Principal principal) {
        return apService.calculate(principal.getName(), req);
    }
}
