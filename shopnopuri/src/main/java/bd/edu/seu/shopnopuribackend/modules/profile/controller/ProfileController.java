package bd.edu.seu.shopnopuribackend.modules.profile.controller;

import bd.edu.seu.shopnopuribackend.modules.profile.dto.ProfileResponse;
import bd.edu.seu.shopnopuribackend.modules.profile.dto.ProfileUpsertRequest;
import bd.edu.seu.shopnopuribackend.modules.profile.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/me")
    public ProfileResponse me(Authentication auth) {
        return profileService.getMyProfile(auth.getName());
    }

    @PostMapping
    public ProfileResponse create(Authentication auth, @Valid @RequestBody ProfileUpsertRequest req) {
        return profileService.createProfile(auth.getName(), req);
    }

    @PutMapping
    public ProfileResponse update(Authentication auth, @Valid @RequestBody ProfileUpsertRequest req) {
        return profileService.updateProfile(auth.getName(), req);
    }
}
