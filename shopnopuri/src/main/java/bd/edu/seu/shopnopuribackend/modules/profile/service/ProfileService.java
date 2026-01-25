package bd.edu.seu.shopnopuribackend.modules.profile.service;

import bd.edu.seu.shopnopuribackend.modules.profile.dto.ProfileResponse;
import bd.edu.seu.shopnopuribackend.modules.profile.dto.ProfileUpsertRequest;
import bd.edu.seu.shopnopuribackend.modules.profile.entity.StudentProfile;
import bd.edu.seu.shopnopuribackend.modules.profile.repository.StudentProfileRepository;
import bd.edu.seu.shopnopuribackend.modules.user.entity.User;
import bd.edu.seu.shopnopuribackend.modules.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final StudentProfileRepository profileRepository;
    private final UserRepository userRepository;

    public ProfileService(StudentProfileRepository profileRepository,
                          UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    public ProfileResponse getMyProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        StudentProfile profile = profileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Profile not found"));

        return toResponse(profile, user.getEmail());
    }

    public ProfileResponse createProfile(String email, ProfileUpsertRequest req) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (profileRepository.existsByUserId(user.getId())) {
            throw new IllegalArgumentException("Profile already exists");
        }

        StudentProfile p = new StudentProfile();
        p.setUser(user);
        apply(p, req);

        StudentProfile saved = profileRepository.save(p);
        return toResponse(saved, user.getEmail());
    }

    public ProfileResponse updateProfile(String email, ProfileUpsertRequest req) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        StudentProfile p = profileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Profile not found"));

        apply(p, req);

        StudentProfile saved = profileRepository.save(p);
        return toResponse(saved, user.getEmail());
    }

    private void apply(StudentProfile p, ProfileUpsertRequest req) {
        p.setFullName(req.getFullName());
        p.setPhone(req.getPhone());
        p.setHscGpa(req.getHscGpa());
        p.setSscGpa(req.getSscGpa());
        p.setBoard(req.getBoard());
        p.setGroupName(req.getGroupName());
        p.setSubjectsJson(req.getSubjectsJson());
        p.setCareerInterest(req.getCareerInterest());
        p.setPreferredLocation(req.getPreferredLocation());
        p.setFamilyIncomeRange(req.getFamilyIncomeRange());
    }

    private ProfileResponse toResponse(StudentProfile p, String email) {
        return new ProfileResponse(
                p.getId(),
                email,
                p.getFullName(),
                p.getPhone(),
                p.getHscGpa(),
                p.getSscGpa(),
                p.getBoard(),
                p.getGroupName(),
                p.getSubjectsJson(),
                p.getCareerInterest(),
                p.getPreferredLocation(),
                p.getFamilyIncomeRange()
        );
    }
}
