package bd.edu.seu.shopnopuribackend.modules.match.svc;

import java.util.List;
import java.util.Map;

public class CareerMap {

    private static final Map<String, List<String>> MAP = Map.ofEntries(
            Map.entry("Software Engineer", List.of("cse", "software", "ict", "computer")),
            Map.entry("Data Scientist", List.of("cse", "statistics", "data", "eee")),
            Map.entry("UX Designer", List.of("design", "cse", "multimedia")),
            Map.entry("Doctor (MBBS)", List.of("mbbs", "medical")),
            Map.entry("Business Leader", List.of("bba", "business", "management", "finance"))
    );

    public List<String> keys(String career) {
        if (career == null) return List.of();
        return MAP.getOrDefault(career.trim(), List.of());
    }
}
