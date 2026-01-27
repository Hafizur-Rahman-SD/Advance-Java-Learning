package bd.edu.seu.shopnopuribackend.modules.Scholarship.controller;

import bd.edu.seu.shopnopuribackend.modules.Scholarship.dto.*;
import bd.edu.seu.shopnopuribackend.modules.Scholarship.service.SchSvc;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin/scholarships")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminScholarshipCtrl {

    private final SchSvc svc;

    @PostMapping
    public SchRes create(@Valid @RequestBody SchReqC req) {
        return svc.create(req);
    }

    @PutMapping("/{id}")
    public SchRes update(@PathVariable Long id, @Valid @RequestBody SchReqU req) {
        return svc.update(id, req);
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> del(@PathVariable Long id) {
        svc.del(id);
        return Map.of("ok", true);
    }
}
