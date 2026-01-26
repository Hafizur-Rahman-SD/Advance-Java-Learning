package bd.edu.seu.shopnopuribackend.modules.Scholarship.controller;


import bd.edu.seu.shopnopuribackend.modules.Scholarship.dto.SchAppReq;
import bd.edu.seu.shopnopuribackend.modules.Scholarship.dto.SchAppRes;
import bd.edu.seu.shopnopuribackend.modules.Scholarship.dto.SchRes;
import bd.edu.seu.shopnopuribackend.modules.Scholarship.service.SchSvc;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/me/sch")
@RequiredArgsConstructor
@PreAuthorize("hasRole('STUDENT')")
public class MySchCtrl {

    private final SchSvc svc;

    @PostMapping("/{id}/save")
    public Map<String, Object> save(@PathVariable Long id, Principal p) {
        svc.save(p.getName(), id);
        return Map.of("ok", true);
    }

    @DeleteMapping("/{id}/save")
    public Map<String, Object> unsave(@PathVariable Long id, Principal p) {
        svc.unsave(p.getName(), id);
        return Map.of("ok", true);
    }

    @GetMapping("/saved")
    public List<SchRes> mySaved(Principal p) {
        return svc.mySaved(p.getName());
    }

    @PostMapping("/{id}/apply")
    public SchAppRes apply(@PathVariable Long id, @Valid @RequestBody SchAppReq req, Principal p) {
        return svc.apply(p.getName(), id, req);
    }

    @GetMapping("/apps")
    public List<SchAppRes> myApps(Principal p) {
        return svc.myApps(p.getName());
    }
}
