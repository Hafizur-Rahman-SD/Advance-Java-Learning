package bd.edu.seu.shopnopuribackend.modules.studyplan.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class StudyPlanCreateReq {

    @NotBlank
    private String targetName;

    @NotNull
    @Min(1) @Max(52)
    private Integer weeks;

    @NotNull
    @Min(1) @Max(12)
    private Integer hoursPerDay;
}
