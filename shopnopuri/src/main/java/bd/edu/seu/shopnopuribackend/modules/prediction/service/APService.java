package bd.edu.seu.shopnopuribackend.modules.prediction.service;

import bd.edu.seu.shopnopuribackend.modules.prediction.dto.APChanceRequest;
import bd.edu.seu.shopnopuribackend.modules.prediction.dto.APResponse;
import bd.edu.seu.shopnopuribackend.modules.prediction.engine.ChanceScoringEngine;
import bd.edu.seu.shopnopuribackend.modules.profile.entity.StudentProfile;
import bd.edu.seu.shopnopuribackend.modules.profile.repository.StudentProfileRepository;
import bd.edu.seu.shopnopuribackend.modules.university.entity.Department;
import bd.edu.seu.shopnopuribackend.modules.university.entity.University;
import bd.edu.seu.shopnopuribackend.modules.university.repository.DepartmentRepository;
import bd.edu.seu.shopnopuribackend.modules.university.repository.UniversityRepository;
import bd.edu.seu.shopnopuribackend.modules.user.entity.User;
import bd.edu.seu.shopnopuribackend.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class APService {

    private final UserRepository userRepository;
    private final StudentProfileRepository profileRepository;
    private final UniversityRepository universityRepository;
    private final DepartmentRepository departmentRepository;

    private final ChanceScoringEngine engine = new ChanceScoringEngine();

    public APResponse calculate(String email, APChanceRequest req) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        StudentProfile profile = profileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Profile not found"));

        University uni = universityRepository.findById(req.getUniversityId())
                .orElseThrow(() -> new IllegalArgumentException("University not found"));

        Department dept = departmentRepository.findById(req.getDepartmentId())
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));

        ChanceScoringEngine.Result r = engine.score(
                profile.getHscGpa(),
                profile.getSubjects(),
                uni.getName(),
                dept.getName(),
                req.getMockScore(),
                req.getExtracurricular()
        );

        return APResponse.builder()
                .chance(r.getChance())
                .confidence(r.getConfidence())
                .factors(r.getFactors())
                .suggestions(r.getSuggestions())
                .build();
    }
}
