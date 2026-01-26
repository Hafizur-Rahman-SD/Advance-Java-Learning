package bd.edu.seu.shopnopuribackend.modules.career.controller;

import bd.edu.seu.shopnopuribackend.modules.career.dto.CareerResultResponse;
import bd.edu.seu.shopnopuribackend.modules.career.service.CareerTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
//for view carrear result endpoint

@RestController
@RequestMapping("/api/me")
@RequiredArgsConstructor
public class MyCareerController {

    private final CareerTestService careerTestService;

    @GetMapping("/career-result")
    public CareerResultResponse myResult(Principal principal) {
        return careerTestService.getLatestResult(principal.getName());
    }
}
