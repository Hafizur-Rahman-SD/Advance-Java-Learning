package bd.edu.seu.shopnopuribackend.modules.pred.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PredRes {
    private int chance;        // 0-100
    private int conf;          // 0-100
    private List<String> facts;
    private List<String> tips;
}
