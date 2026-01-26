package bd.edu.seu.shopnopuribackend.modules.career.service;

import bd.edu.seu.shopnopuribackend.modules.career.dto.CareerResultResponse;
import bd.edu.seu.shopnopuribackend.modules.career.entity.CareerDimension;

import java.util.*;
import java.util.stream.Collectors;

public class CareerScoringEngine {

    // Career weights (simple V1)
    private static final Map<String, Map<CareerDimension, Integer>> CAREER_WEIGHTS = Map.of(
            "Software Engineer", Map.of(
                    CareerDimension.ANALYTICAL, 5,
                    CareerDimension.DETAIL, 4,
                    CareerDimension.CREATIVE, 2,
                    CareerDimension.SOCIAL, 1,
                    CareerDimension.LEADERSHIP, 1
            ),
            "Data Scientist", Map.of(
                    CareerDimension.ANALYTICAL, 5,
                    CareerDimension.DETAIL, 4,
                    CareerDimension.CREATIVE, 2,
                    CareerDimension.SOCIAL, 1,
                    CareerDimension.LEADERSHIP, 1
            ),
            "UX Designer", Map.of(
                    CareerDimension.CREATIVE, 5,
                    CareerDimension.SOCIAL, 3,
                    CareerDimension.DETAIL, 3,
                    CareerDimension.ANALYTICAL, 2,
                    CareerDimension.LEADERSHIP, 1
            ),
            "Doctor (MBBS)", Map.of(
                    CareerDimension.DETAIL, 5,
                    CareerDimension.SOCIAL, 4,
                    CareerDimension.ANALYTICAL, 3,
                    CareerDimension.LEADERSHIP, 2,
                    CareerDimension.CREATIVE, 1
            ),
            "Business Leader", Map.of(
                    CareerDimension.LEADERSHIP, 5,
                    CareerDimension.SOCIAL, 4,
                    CareerDimension.ANALYTICAL, 3,
                    CareerDimension.CREATIVE, 2,
                    CareerDimension.DETAIL, 2
            )
    );

    public CareerResultResponse score(Map<CareerDimension, Integer> dimensionScore) {
        // Calculate max possible score in this test scale is not fixed here; we normalize by weighted sum
        Map<String, Integer> careerRaw = new HashMap<>();

        for (var entry : CAREER_WEIGHTS.entrySet()) {
            String career = entry.getKey();
            Map<CareerDimension, Integer> weights = entry.getValue();

            int sum = 0;
            int max = 0;
            for (CareerDimension d : CareerDimension.values()) {
                int w = weights.getOrDefault(d, 0);
                int v = dimensionScore.getOrDefault(d, 0);
                sum += (v * w);
                // assume max per dimension around 20 in V1 (depends on #questions). Use 20 for normalization.
                max += (20 * w);
            }
            int percent = max == 0 ? 0 : (int) Math.round((sum * 100.0) / max);
            careerRaw.put(career, clamp(percent, 0, 100));
        }

        List<CareerResultResponse.CareerMatch> top3 = careerRaw.entrySet().stream()
                .sorted((a, b) -> Integer.compare(b.getValue(), a.getValue()))
                .limit(3)
                .map(e -> CareerResultResponse.CareerMatch.builder()
                        .career(e.getKey())
                        .matchPercent(e.getValue())
                        .build())
                .collect(Collectors.toList());

        String personality = buildPersonalitySummary(dimensionScore);

        List<String> suggestions = new ArrayList<>();
        suggestions.add("Choose 1 career from Top 3 and start small projects.");
        suggestions.add("Use your dimension strengths to plan your study roadmap.");

        return CareerResultResponse.builder()
                .personalitySummary(personality)
                .dimensionScore(toStringKeyMap(dimensionScore))
                .topCareers(top3)
                .suggestions(suggestions)
                .build();
    }

    private String buildPersonalitySummary(Map<CareerDimension, Integer> dimensionScore) {
        return dimensionScore.entrySet().stream()
                .sorted((a, b) -> Integer.compare(b.getValue(), a.getValue()))
                .limit(2)
                .map(e -> e.getKey().name())
                .collect(Collectors.joining(" & "));
    }

    private Map<String, Integer> toStringKeyMap(Map<CareerDimension, Integer> dimensionScore) {
        Map<String, Integer> map = new LinkedHashMap<>();
        for (var e : dimensionScore.entrySet()) {
            map.put(e.getKey().name(), e.getValue());
        }
        return map;
    }

    private int clamp(int v, int min, int max) {
        return Math.max(min, Math.min(max, v));
    }
}
