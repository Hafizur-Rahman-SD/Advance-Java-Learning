package bd.edu.seu.shopnopuribackend.modules.matchmaker;


import java.util.List;
import java.util.Map;

public class CareerDeptMapper {

    private static final Map<String, List<String>> MAP = Map.ofEntries(
            Map.entry("Software Engineer", List.of("cse", "software", "ict", "computer")),
            Map.entry("Data Scientist", List.of("cse", "statistics", "data", "eee", "computer")),
            Map.entry("UX Designer", List.of("cse", "design", "cse", "computer", "multimedia")),
            Map.entry("Doctor (MBBS)", List.of("mbbs", "medical")),
            Map.entry("Business Leader", List.of("bba", "business", "management", "finance"))
    );

    public List<String> keywordsForCareer(String career) {
        if (career == null) return List.of();
        return MAP.getOrDefault(career.trim(), List.of());
    }
}
