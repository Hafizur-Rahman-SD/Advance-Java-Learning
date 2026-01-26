package bd.edu.seu.shopnopuribackend.modules.studyplan.controller;

import bd.edu.seu.shopnopuribackend.modules.studyplan.dto.StudyPlanCreateReq;
import bd.edu.seu.shopnopuribackend.modules.studyplan.dto.StudyPlanRes;
import bd.edu.seu.shopnopuribackend.modules.studyplan.service.StudyPlanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/study-plans")
@RequiredArgsConstructor
public class StudyPlanController {

    private final StudyPlanService studyPlanService;

    @PostMapping
    public StudyPlanRes create(@Valid @RequestBody StudyPlanCreateReq req, Principal principal) {
        return studyPlanService.create(principal.getName(), req);
    }

    @GetMapping("/me")
    public List<StudyPlanRes> myPlans(Principal principal) {
        return studyPlanService.myPlans(principal.getName());
    }

    @GetMapping("/{id}")
    public StudyPlanRes getOne(@PathVariable Long id, Principal principal) {
        return studyPlanService.getOne(principal.getName(), id);
    }

    @PostMapping("/{planId}/tasks/{taskId}/complete")
    public StudyPlanRes complete(@PathVariable Long planId, @PathVariable Long taskId, Principal principal) {
        return studyPlanService.markComplete(principal.getName(), planId, taskId);
    }

    @PostMapping("/{planId}/regenerate")
    public StudyPlanRes regenerate(@PathVariable Long planId, Principal principal) {
        return studyPlanService.regenerate(principal.getName(), planId);
    }
}
