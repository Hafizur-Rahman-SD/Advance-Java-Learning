package bd.edu.seu.shopnopuribackend.modules.match.svc;

import bd.edu.seu.shopnopuribackend.modules.career.repository.CareerTestAttemptRepository;
import bd.edu.seu.shopnopuribackend.modules.match.dto.MatchItem;
import bd.edu.seu.shopnopuribackend.modules.match.dto.MatchRes;
import bd.edu.seu.shopnopuribackend.modules.university.entity.Department;
import bd.edu.seu.shopnopuribackend.modules.university.repository.DepartmentRepository;
import bd.edu.seu.shopnopuribackend.modules.user.entity.User;
import bd.edu.seu.shopnopuribackend.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MatchSvc {

    private final DepartmentRepository deptRepo;
    private final UserRepository userRepo;
    private final CareerTestAttemptRepository attemptRepo;

    private final CareerMap map = new CareerMap();

    public MatchRes byCareer(String career) {
        List<String> keys = map.keys(career);
        List<MatchItem> items = suggest(keys);
        return MatchRes.builder()
                .career(career)
                .keys(keys)
                .items(items)
                .build();
    }

    // Auto: read latest career result for user, pick top career
    public MatchRes byEmail(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        var attempt = attemptRepo.findTopByUserIdOrderBySubmittedAtDesc(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("No career test result found"));

        // V1: result_json contains "career":"Software Engineer"
        String topCareer = extractTopCareer(attempt.getResultJson());
        if (topCareer == null) topCareer = "Software Engineer";

        return byCareer(topCareer);
    }

    private List<MatchItem> suggest(List<String> keys) {
        if (keys == null || keys.isEmpty()) return List.of();

        List<Department> all = deptRepo.findAll();
        List<MatchItem> out = new ArrayList<>();

        for (Department d : all) {
            if (!containsAny(d.getName(), keys)) continue;

            String uni = d.getUniversity().getName();
            String cat = cat(uni);

            out.add(MatchItem.builder()
                    .uniId(d.getUniversity().getId())
                    .uni(uni)
                    .deptId(d.getId())
                    .dept(d.getName())
                    .cat(cat)
                    .note(note(cat))
                    .build());

            if (out.size() >= 20) break;
        }

        return out;
    }

    private boolean containsAny(String deptName, List<String> keys) {
        if (deptName == null) return false;
        String n = deptName.toLowerCase();
        for (String k : keys) {
            if (n.contains(k.toLowerCase())) return true;
        }
        return false;
    }

    private String cat(String uniName) {
        String u = uniName == null ? "" : uniName.toLowerCase();
        if (u.contains("buet") || u.contains("dhaka") || u.contains("du")) return "REACH";
        if (u.contains("brac") || u.contains("nsu") || u.contains("north south")) return "SAFE";
        return "TARGET";
    }

    private String note(String cat) {
        return switch (cat) {
            case "REACH" -> "Highly competitive.";
            case "SAFE" -> "Good chance for most students.";
            default -> "Balanced option.";
        };
    }

    // Very small parser for V1 stored JSON string
    private String extractTopCareer(String json) {
        if (json == null) return null;

        // tries to find first {"career":"..."}
        int idx = json.indexOf("\"career\":\"");
        if (idx < 0) return null;
        int start = idx + "\"career\":\"".length();
        int end = json.indexOf("\"", start);
        if (end < 0) return null;
        return json.substring(start, end);
    }
}
