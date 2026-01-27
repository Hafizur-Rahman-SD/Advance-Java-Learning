package bd.edu.seu.shopnopuribackend.modules.UniApp.controller;

import bd.edu.seu.shopnopuribackend.modules.UniApp.dto.*;
import bd.edu.seu.shopnopuribackend.modules.UniApp.service.UAppSvc;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/me/apps")
@RequiredArgsConstructor
@PreAuthorize("hasRole('STUDENT')")
public class UAppCtrl {

    private final UAppSvc svc;

    private String email(Authentication auth) {
        return auth.getName(); // your JWT sets username=email already
    }

    @GetMapping
    public List<UAppRes> my(Authentication auth) {
        return svc.myList(email(auth));
    }

    @PostMapping
    public UAppRes create(Authentication auth, @Valid @RequestBody UAppReqC req) {
        return svc.create(email(auth), req);
    }

    @PutMapping("/{id}")
    public UAppRes update(Authentication auth, @PathVariable Long id, @Valid @RequestBody UAppReqU req) {
        return svc.update(email(auth), id, req);
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> del(Authentication auth, @PathVariable Long id) {
        svc.del(email(auth), id);
        return Map.of("ok", true);
    }
}
