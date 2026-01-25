package bd.edu.seu.shopnopuribackend.modules.prediction.engine;

import bd.edu.seu.shopnopuribackend.modules.prediction.dto.FactorScore;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ChanceScoringEngine {

    @Getter
    @AllArgsConstructor
    public static class Result {
        private int chance;
        private String confidence;
        private List<FactorScore> factors;
        private List<String> suggestions;
    }

    public Result score(BigDecimal hscGpa, String subjects, String universityName, String departmentName,
                        Integer mockScore, Integer extracurricular) {

        List<FactorScore> factors = new ArrayList<>();
        List<String> suggestions = new ArrayList<>();

        int total = 0;

        // 1) GPA (max 50)
        int gpaScore = calcGpaScore(hscGpa);
        total += gpaScore;
        factors.add(FactorScore.builder()
                .name("GPA")
                .score(gpaScore)
                .maxScore(50)
                .note(gpaScore >= 40 ? "Good" : "Need Improvement")
                .build());

        // 2) Subject match (max 20)
        int subjectScore = calcSubjectMatch(subjects, departmentName);
        total += subjectScore;
        factors.add(FactorScore.builder()
                .name("Subject Match")
                .score(subjectScore)
                .maxScore(20)
                .note(subjectScore >= 15 ? "Excellent" : "Medium")
                .build());

        // 3) Mock (max 20)
        int mock = calcMockScore(mockScore);
        total += mock;
        factors.add(FactorScore.builder()
                .name("Mock Score")
                .score(mock)
                .maxScore(20)
                .note(mock >= 14 ? "Good" : "Need Practice")
                .build());

        // 4) Competition (max 10)
        int comp = calcCompetition(universityName);
        total += comp;
        factors.add(FactorScore.builder()
                .name("Competition")
                .score(comp)
                .maxScore(10)
                .note(comp >= 7 ? "Medium" : "High")
                .build());

        // 5) Extra (bonus max +5 but keep cap 100)
        int extraBonus = calcExtraBonus(extracurricular);
        total += extraBonus;
        if (extracurricular != null) {
            factors.add(FactorScore.builder()
                    .name("Extracurricular")
                    .score(extraBonus)
                    .maxScore(5)
                    .note(extraBonus >= 3 ? "Helpful" : "Low")
                    .build());
        }

        int chance = Math.max(0, Math.min(100, total));

        // Suggestions
        if (mockScore == null || mockScore < 75) suggestions.add("Increase mock test score to 75+");
        if (subjectScore < 15) suggestions.add("Improve subject preparation for target department");
        if (gpaScore < 40) suggestions.add("Improve HSC GPA if possible (revision + mock practice)");
        if (extracurricular == null || extracurricular < 5) suggestions.add("Add 1-2 relevant activities/projects");

        String confidence = chance >= 75 ? "HIGH" : (chance >= 55 ? "MEDIUM" : "LOW");

        return new Result(chance, confidence, factors, suggestions);
    }

    private int calcGpaScore(BigDecimal hscGpa) {
        if (hscGpa == null) return 0;
        double gpa = hscGpa.doubleValue();
        int score = (int) Math.round((gpa / 5.0) * 50);
        return clamp(score, 0, 50);
    }

    private int calcSubjectMatch(String subjects, String deptName) {
        String s = subjects == null ? "" : subjects.toLowerCase();
        String d = deptName == null ? "" : deptName.toLowerCase();

        int score = 10; // base
        if (d.contains("cse") || d.contains("computer") || d.contains("software")) {
            if (s.contains("math")) score += 5;
            if (s.contains("ict")) score += 5;
        } else if (d.contains("eee") || d.contains("electrical")) {
            if (s.contains("physics")) score += 5;
            if (s.contains("math")) score += 5;
        } else if (d.contains("bba") || d.contains("business")) {
            if (s.contains("math")) score += 5;
        } else if (d.contains("mbbs") || d.contains("medical")) {
            if (s.contains("biology")) score += 10;
        }

        return clamp(score, 0, 20);
    }

    private int calcMockScore(Integer mockScore) {
        if (mockScore == null) return 8; // base
        int score = (int) Math.round((mockScore / 100.0) * 20);
        return clamp(score, 0, 20);
    }

    private int calcCompetition(String uniName) {
        String u = uniName == null ? "" : uniName.toLowerCase();

        // High competition => lower points
        if (u.contains("buet")) return 5;
        if (u.contains("dhaka") || u.contains("du")) return 5;
        return 8;
    }

    private int calcExtraBonus(Integer extracurricular) {
        if (extracurricular == null) return 0;
        // 0-10 -> 0-5
        int score = (int) Math.round((extracurricular / 10.0) * 5);
        return clamp(score, 0, 5);
    }

    private int clamp(int val, int min, int max) {
        return Math.max(min, Math.min(max, val));
    }
}
