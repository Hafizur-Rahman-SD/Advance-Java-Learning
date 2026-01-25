package bd.edu.seu.shopnopuribackend.modules.university.controller;

import bd.edu.seu.shopnopuribackend.modules.university.dto.DepartmentResponse;
import bd.edu.seu.shopnopuribackend.modules.university.dto.UpdateDepartmentRequest;
import bd.edu.seu.shopnopuribackend.modules.university.service.UniversityService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final UniversityService universityService;

    public DepartmentController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @PutMapping("/{id}")
    public DepartmentResponse update(@PathVariable Long id,
                                     @Valid @RequestBody UpdateDepartmentRequest req) {
        return universityService.updateDepartment(id, req);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        universityService.deleteDepartment(id);
        return "Department deleted";
    }
}
