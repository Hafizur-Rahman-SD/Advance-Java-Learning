package bd.edu.seu.shopnopuribackend.modules.pred.svc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PredEng {

    public Result calc(
            BigDecimal hscGpa,
            String group,
            boolean careerAligned,
            int deptDiff,        // 1..3 (1 easy, 3 hard)
            Integer mock,        // 0..100
            Integer extra        // 0..10
    ) {
        int score = 0;
        List<String> facts = new ArrayList<>();
        List<String> tips = new ArrayList<>();

        // 1) GPA (0..50)
        int g = gpaScore(hscGpa);
        score += g;
        facts.add("GPA score: " + g + "/50");

        // 2) Group bonus (0..10)
        int gb = groupBonus(group);
        score += gb;
        facts.add("Group bonus: " + gb + "/10");

        // 3) Career alignment (0..10)
        int ca = careerAligned ? 10 : 4;
        score += ca;
        facts.add("Career alignment: " + ca + "/10");

        // 4) Mock (0..20)
        int ms = mockScore(mock);
        score += ms;
        facts.add("Mock performance: " + ms + "/20");

        // 5) Extracurricular (0..10)
        int ex = extraScore(extra);
        score += ex;
        facts.add("Extracurricular: " + ex + "/10");

        // Difficulty penalty
        int pen = (deptDiff == 3) ? 12 : (deptDiff == 2 ? 6 : 2);
        score -= pen;
        facts.add("Competition penalty: -" + pen);

        int chance = clamp(score, 0, 100);

        int conf = confidence(hscGpa, mock);
        tips.addAll(defaultTips(mock, careerAligned, deptDiff));

        return new Result(chance, conf, facts, tips);
    }

    private int gpaScore(BigDecimal hsc) {
        if (hsc == null) return 0;
        // GPA 5 => 50, GPA 4 => 35 approx
        double v = hsc.doubleValue();
        int score = (int) Math.round((v / 5.0) * 50);
        return clamp(score, 0, 50);
    }

    private int groupBonus(String group) {
        if (group == null) return 0;
        String g = group.toLowerCase();
        if (g.contains("science")) return 10;
        if (g.contains("commerce")) return 7;
        if (g.contains("arts")) return 5;
        return 0;
    }

    private int mockScore(Integer mock) {
        if (mock == null) return 8; // neutral default
        return clamp((int) Math.round((mock / 100.0) * 20), 0, 20);
    }

    private int extraScore(Integer extra) {
        if (extra == null) return 3;
        return clamp(extra, 0, 10);
    }

    private int confidence(BigDecimal gpa, Integer mock) {
        int c = 65;
        if (gpa != null && gpa.doubleValue() >= 4.5) c += 10;
        if (mock != null) c += 10;
        return clamp(c, 40, 90);
    }

    private List<String> defaultTips(Integer mock, boolean aligned, int diff) {
        List<String> tips = new ArrayList<>();
        if (mock == null || mock < 70) tips.add("Increase mock score to 75+ for better chance.");
        if (!aligned) tips.add("Your career preference is not strongly aligned with this department.");
        if (diff == 3) tips.add("This department is highly competitive. Focus on core subjects and speed practice.");
        tips.add("Solve previous year questions and review weak topics weekly.");
        return tips;
    }

    private int clamp(int v, int min, int max) {
        return Math.max(min, Math.min(max, v));
    }

    public record Result(int chance, int conf, List<String> facts, List<String> tips) {}
}
