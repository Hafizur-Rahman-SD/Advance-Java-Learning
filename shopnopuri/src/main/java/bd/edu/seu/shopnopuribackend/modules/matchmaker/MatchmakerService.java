package bd.edu.seu.shopnopuribackend.modules.matchmaker;


import bd.edu.seu.shopnopuribackend.modules.matchmaker.MatchmakerResponse;
import bd.edu.seu.shopnopuribackend.modules.university.entity.Department;
import bd.edu.seu.shopnopuribackend.modules.university.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MatchmakerService {

    private final DepartmentRepository departmentRepository;
    private final CareerDeptMapper mapper = new CareerDeptMapper();

    public MatchmakerResponse suggestByCareer(String career) {

        List<String> keywords = mapper.keywordsForCareer(career);
        if (keywords.isEmpty()) {
            return MatchmakerResponse.builder()
                    .career(career)
                    .matchedDepartmentKeywords(List.of())
                    .suggestions(List.of())
                    .build();
        }

        // pull all departments (V1) then filter (later optimize with query)
        List<Department> all = departmentRepository.findAll();

        List<Department> matched = all.stream()
                .filter(d -> containsAny(d.getName(), keywords))
                .limit(20)
                .toList();

        List<MatchmakerResponse.Suggestion> suggestions = new ArrayList<>();

        for (Department d : matched) {
            String uniName = d.getUniversity().getName();
            String category = categorize(uniName);
            suggestions.add(MatchmakerResponse.Suggestion.builder()
                    .universityId(d.getUniversity().getId())
                    .universityName(d.getUniversity().getName())
                    .departmentId(d.getId())
                    .departmentName(d.getName())
                    .category(category)
                    .note(noteFor(category))
                    .build());
        }

        return MatchmakerResponse.builder()
                .career(career)
                .matchedDepartmentKeywords(keywords)
                .suggestions(suggestions)
                .build();
    }

    private boolean containsAny(String name, List<String> keywords) {
        if (name == null) return false;
        String n = name.toLowerCase();
        for (String k : keywords) {
            if (n.contains(k.toLowerCase())) return true;
        }
        return false;
    }

    // V1: basic categorization by university name (later use competition_level from DB)
    private String categorize(String uniName) {
        String u = (uniName == null) ? "" : uniName.toLowerCase();
        if (u.contains("buet") || u.contains("dhaka") || u.contains("du")) return "REACH";
        if (u.contains("brac") || u.contains("nsu") || u.contains("north south")) return "SAFE";
        return "TARGET";
    }

    private String noteFor(String category) {
        return switch (category) {
            case "REACH" -> "Highly competitive, prepare strongly.";
            case "SAFE" -> "Good chance based on typical patterns.";
            default -> "Balanced option for most students.";
        };
    }
}
