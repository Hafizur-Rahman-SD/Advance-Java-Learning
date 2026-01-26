package bd.edu.seu.shopnopuribackend.modules.career.controller;

import bd.edu.seu.shopnopuribackend.modules.career.dto.*;
import bd.edu.seu.shopnopuribackend.modules.career.service.CareerTestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/career-test")
@RequiredArgsConstructor
public class CareerTestController {

    private final CareerTestService careerTestService;

    @GetMapping("/questions")
    public List<CareerQuestionResponse> questions() {
        return careerTestService.getActiveQuestions();
    }

    @PostMapping("/submit")
    public CareerResultResponse submit(@Valid @RequestBody CareerSubmitRequest req, Principal principal) {
        return careerTestService.submit(principal.getName(), req);
    }
}
