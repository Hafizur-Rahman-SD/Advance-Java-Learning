package bd.edu.seu.shopnopuribackend.modules.career.dto;

import bd.edu.seu.shopnopuribackend.modules.career.entity.CareerDimension;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CareerQuestionResponse {
    private Long id;
    private String questionText;
    private CareerDimension dimension;
}
