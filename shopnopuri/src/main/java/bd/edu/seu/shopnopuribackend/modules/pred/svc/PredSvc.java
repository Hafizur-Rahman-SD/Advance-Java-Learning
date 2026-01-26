package bd.edu.seu.shopnopuribackend.modules.pred.svc;

import bd.edu.seu.shopnopuribackend.modules.career.repository.CareerTestAttemptRepository;
import bd.edu.seu.shopnopuribackend.modules.pred.dto.PredReq;
import bd.edu.seu.shopnopuribackend.modules.pred.dto.PredRes;
import bd.edu.seu.shopnopuribackend.modules.profile.repository.StudentProfileRepository;
import bd.edu.seu.shopnopuribackend.modules.university.repository.DepartmentRepository;
import bd.edu.seu.shopnopuribackend.modules.user.entity.User;
import bd.edu.seu.shopnopuribackend.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PredSvc {

    private final UserRepository userRepo;
    private final StudentProfileRepository profileRepo;
    private final CareerTestAttemptRepository attemptRepo;
    private final DepartmentRepository deptRepo;

    private final PredEng eng = new PredEng();

    public PredRes calcFor(String email, PredReq req) {
        User u = userRepo.findByEmail(email).orElseThrow();

        var profile = profileRepo.findByUserId(u.getId())
                .orElseThrow(() -> new IllegalArgumentException("Profile not found"));

        // Pull top career from latest attempt
        String topCareer = attemptRepo.findTopByUserIdOrderBySubmittedAtDesc(u.getId())
                .map(a -> extractTopCareer(a.getResultJson()))
                .orElse(null);

        // Career aligned? (simple check with dept name)
        String deptName = deptRepo.findById(req.getDeptId()).orElseThrow().getName();
        boolean aligned = isAligned(topCareer, deptName);

        int diff = deptDiff(req.getUniId(), deptName);

        BigDecimal hsc = profile.getHscGpa();
        String group = profile.getGroupName();

        var r = eng.calc(hsc, group, aligned, diff, req.getMock(), req.getExtra());

        return PredRes.builder()
                .chance(r.chance())
                .conf(r.conf())
                .facts(r.facts())
                .tips(r.tips())
                .build();
    }

    private boolean isAligned(String career, String deptName) {
        if (career == null || deptName == null) return true;
        String c = career.toLowerCase();
        String d = deptName.toLowerCase();
        if (c.contains("software") || c.contains("data")) return d.contains("cse") || d.contains("computer") || d.contains("ict") || d.contains("software");
        if (c.contains("doctor") || c.contains("mbbs")) return d.contains("mbbs") || d.contains("medical");
        if (c.contains("business")) return d.contains("bba") || d.contains("management") || d.contains("business");
        if (c.contains("ux")) return d.contains("design") || d.contains("cse") || d.contains("multimedia");
        return true;
    }

    // V1 heuristic: Later you will store competition_level in DB
    private int deptDiff(Long uniId, String deptName) {
        String d = deptName == null ? "" : deptName.toLowerCase();
        if (d.contains("cse") || d.contains("mbbs")) return 3;
        if (d.contains("eee") || d.contains("bba")) return 2;
        return 1;
    }

    private String extractTopCareer(String json) {
        if (json == null) return null;
        int idx = json.indexOf("\"career\":\"");
        if (idx < 0) return null;
        int start = idx + "\"career\":\"".length();
        int end = json.indexOf("\"", start);
        if (end < 0) return null;
        return json.substring(start, end);
    }
}
