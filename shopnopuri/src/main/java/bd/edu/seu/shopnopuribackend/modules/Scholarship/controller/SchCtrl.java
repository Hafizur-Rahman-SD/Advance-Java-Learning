package bd.edu.seu.shopnopuribackend.modules.Scholarship.controller;


import bd.edu.seu.shopnopuribackend.modules.Scholarship.dto.SchRes;
import bd.edu.seu.shopnopuribackend.modules.Scholarship.service.SchSvc;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sch")
@RequiredArgsConstructor
public class SchCtrl {

    private final SchSvc svc;

    @GetMapping
    public Page<SchRes> list(
            @RequestParam(defaultValue = "") String country,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return svc.list(country, page, size);
    }

    @GetMapping("/{id}")
    public SchRes get(@PathVariable Long id) {
        return svc.get(id);
    }
}
