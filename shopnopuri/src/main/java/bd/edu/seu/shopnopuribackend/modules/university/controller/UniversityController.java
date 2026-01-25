package bd.edu.seu.shopnopuribackend.modules.university.controller;

import bd.edu.seu.shopnopuribackend.modules.university.dto.DepartmentResponse;
import bd.edu.seu.shopnopuribackend.modules.university.dto.UniversityResponse;
import bd.edu.seu.shopnopuribackend.modules.university.service.UniversityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import bd.edu.seu.shopnopuribackend.modules.university.dto.CreateDepartmentRequest;
import bd.edu.seu.shopnopuribackend.modules.university.dto.CreateUniversityRequest;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/universities")
public class UniversityController {

    private final UniversityService universityService;

    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    // GET /api/universities?q=du
    @GetMapping
    public List<UniversityResponse> list(@RequestParam(required = false) String q) {
        return universityService.getAllUniversities(q);
    }

    // GET /api/universities/{id}
    @GetMapping("/{id}")
    public UniversityResponse getOne(@PathVariable Long id) {
        return universityService.getUniversityById(id);
    }

    // GET /api/universities/{id}/departments
    @GetMapping("/{id}/departments")
    public List<DepartmentResponse> departments(@PathVariable Long id) {
        return universityService.getDepartmentsByUniversity(id);
    }

    @PostMapping
    public UniversityResponse createUniversity(@Valid @RequestBody CreateUniversityRequest req) {
        return universityService.createUniversity(req);
    }

    @PostMapping("/departments")
    public DepartmentResponse createDepartment(@Valid @RequestBody CreateDepartmentRequest req) {
        return universityService.createDepartment(req);
    }


    @PutMapping("/{id}")
    public UniversityResponse updateUniversity(
            @PathVariable Long id,
            @RequestBody CreateUniversityRequest req) {
        return universityService.updateUniversity(id, req);
    }

    @DeleteMapping("/{id}")
    public String deleteUniversity(@PathVariable Long id) {
        universityService.deleteUniversity(id);
        return "University deleted";
    }






}
