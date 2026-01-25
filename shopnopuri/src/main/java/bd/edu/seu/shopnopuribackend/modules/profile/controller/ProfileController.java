package bd.edu.seu.shopnopuribackend.modules.profile.controller;

import bd.edu.seu.shopnopuribackend.modules.profile.dto.ProfileResponse;
import bd.edu.seu.shopnopuribackend.modules.profile.dto.ProfileUpsertRequest;
import bd.edu.seu.shopnopuribackend.modules.profile.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/me/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public ProfileResponse getMyProfile(Principal principal) {
        return profileService.getMyProfile(principal.getName());
    }

    @PostMapping
    public ProfileResponse createMyProfile(@Valid @RequestBody ProfileUpsertRequest req, Principal principal) {
        return profileService.createMyProfile(principal.getName(), req);
    }

    @PutMapping
    public ProfileResponse updateMyProfile(@Valid @RequestBody ProfileUpsertRequest req, Principal principal) {
        return profileService.updateMyProfile(principal.getName(), req);
    }

    @DeleteMapping
    public String deleteMyProfile(Principal principal) {
        profileService.deleteMyProfile(principal.getName());
        return "Profile deleted";
    }
}
