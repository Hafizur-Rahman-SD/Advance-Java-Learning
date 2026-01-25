package bd.edu.seu.shopnopuribackend.modules.profile.service;

import bd.edu.seu.shopnopuribackend.modules.profile.dto.ProfileResponse;
import bd.edu.seu.shopnopuribackend.modules.profile.dto.ProfileUpsertRequest;
import bd.edu.seu.shopnopuribackend.modules.profile.entity.StudentProfile;
import bd.edu.seu.shopnopuribackend.modules.profile.repository.StudentProfileRepository;
import bd.edu.seu.shopnopuribackend.modules.user.entity.Role;
import bd.edu.seu.shopnopuribackend.modules.user.entity.User;
import bd.edu.seu.shopnopuribackend.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfileService {

    private final StudentProfileRepository profileRepository;
    private final UserRepository userRepository;

    public ProfileResponse getMyProfile(String email) {
        User user = getStudentUserByEmail(email);

        StudentProfile profile = profileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Profile not found"));

        return toResponse(profile);
    }

    public ProfileResponse createMyProfile(String email, ProfileUpsertRequest req) {
        User user = getStudentUserByEmail(email);

        if (profileRepository.existsByUserId(user.getId())) {
            throw new IllegalArgumentException("Profile already exists");
        }

        StudentProfile profile = StudentProfile.builder()
                .user(user)
                .fullName(req.getFullName())
                .phone(req.getPhone())
                .sscGpa(req.getSscGpa())
                .hscGpa(req.getHscGpa())
                .board(req.getBoard())
                .groupName(req.getGroupName())
                .subjects(req.getSubjects())
                .careerInterest(req.getCareerInterest())
                .preferredLocation(req.getPreferredLocation())
                .familyIncomeRange(req.getFamilyIncomeRange())
                .build();

        StudentProfile saved = profileRepository.save(profile);
        return toResponse(saved);
    }

    public ProfileResponse updateMyProfile(String email, ProfileUpsertRequest req) {
        User user = getStudentUserByEmail(email);

        StudentProfile profile = profileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Profile not found"));

        profile.setFullName(req.getFullName());
        profile.setPhone(req.getPhone());
        profile.setSscGpa(req.getSscGpa());
        profile.setHscGpa(req.getHscGpa());
        profile.setBoard(req.getBoard());
        profile.setGroupName(req.getGroupName());
        profile.setSubjects(req.getSubjects());
        profile.setCareerInterest(req.getCareerInterest());
        profile.setPreferredLocation(req.getPreferredLocation());
        profile.setFamilyIncomeRange(req.getFamilyIncomeRange());

        StudentProfile saved = profileRepository.save(profile);
        return toResponse(saved);
    }

    public void deleteMyProfile(String email) {
        User user = getStudentUserByEmail(email);

        StudentProfile profile = profileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Profile not found"));

        profileRepository.delete(profile);
    }

    private User getStudentUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (user.getRole() != Role.STUDENT) {
            throw new IllegalArgumentException("Only STUDENT can use this endpoint");
        }
        if (!user.isEnabled()) {
            throw new IllegalArgumentException("User is disabled");
        }

        return user;
    }

    private ProfileResponse toResponse(StudentProfile p) {
        return ProfileResponse.builder()
                .id(p.getId())
                .userId(p.getUser().getId())
                .email(p.getUser().getEmail())
                .fullName(p.getFullName())
                .phone(p.getPhone())
                .sscGpa(p.getSscGpa())
                .hscGpa(p.getHscGpa())
                .board(p.getBoard())
                .groupName(p.getGroupName())
                .subjects(p.getSubjects())
                .careerInterest(p.getCareerInterest())
                .preferredLocation(p.getPreferredLocation())
                .familyIncomeRange(p.getFamilyIncomeRange())
                .build();
    }
}
