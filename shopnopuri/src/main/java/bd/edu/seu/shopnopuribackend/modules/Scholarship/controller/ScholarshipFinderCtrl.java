package bd.edu.seu.shopnopuribackend.modules.Scholarship.controller;

import bd.edu.seu.shopnopuribackend.modules.Scholarship.dto.SchRes;
import bd.edu.seu.shopnopuribackend.modules.Scholarship.service.SchSvc;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/scholarships")
@RequiredArgsConstructor
public class ScholarshipFinderCtrl {

    private final SchSvc svc;

    @GetMapping
    public Page<SchRes> search(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String district,
            @RequestParam(required = false) String university,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) Double gpa,
            @RequestParam(required = false) Integer income,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return svc.search(category, district, university, gender, gpa, income, page, size);
    }
}
