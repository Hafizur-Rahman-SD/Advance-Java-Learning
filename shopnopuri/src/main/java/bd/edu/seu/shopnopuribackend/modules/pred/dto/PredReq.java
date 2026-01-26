package bd.edu.seu.shopnopuribackend.modules.pred.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PredReq {

    @NotNull
    private Long uniId;

    @NotNull
    private Long deptId;

    @Min(0) @Max(100)
    private Integer mock; // optional

    @Min(0) @Max(10)
    private Integer extra; // optional (0-10)
}
