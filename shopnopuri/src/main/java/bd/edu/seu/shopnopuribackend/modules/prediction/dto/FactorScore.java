package bd.edu.seu.shopnopuribackend.modules.prediction.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FactorScore {
    private String name;
    private int score;
    private int maxScore;
    private String note;
}
