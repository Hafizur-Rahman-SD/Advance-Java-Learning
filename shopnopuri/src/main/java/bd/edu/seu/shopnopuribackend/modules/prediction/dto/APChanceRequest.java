package bd.edu.seu.shopnopuribackend.modules.prediction.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class APChanceRequest {

    @NotNull
    private Long universityId;

    @NotNull
    private Long departmentId;

    // optional
    @Min(0) @Max(100)
    private Integer mockScore;

    // optional (0-10)
    @Min(0) @Max(10)
    private Integer extracurricular;
}
