package bd.edu.seu.shopnopuribackend.modules.prediction.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class APResponse {

    private int chance;                 // 0-100
    private String confidence;           // LOW/MEDIUM/HIGH
    private List<FactorScore> factors;
    private List<String> suggestions;
}
