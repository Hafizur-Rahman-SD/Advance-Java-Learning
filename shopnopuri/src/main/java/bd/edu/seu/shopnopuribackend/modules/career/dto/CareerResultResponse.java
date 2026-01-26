package bd.edu.seu.shopnopuribackend.modules.career.dto;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CareerResultResponse {

    private String personalitySummary;          // e.g. "ANALYTICAL & DETAIL"
    private Map<String, Integer> dimensionScore; // e.g. {"ANALYTICAL": 18, ...}
    private List<CareerMatch> topCareers;        // top 3
    private List<String> suggestions;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CareerMatch {
        private String career;
        private int matchPercent;
    }
}
