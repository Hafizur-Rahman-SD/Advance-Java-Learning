package bd.edu.seu.shopnopuribackend.modules.profile.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileUpsertRequest {

    @NotBlank
    private String fullName;

    private String phone;
    private BigDecimal sscGpa;
    private BigDecimal hscGpa;
    private String board;
    private String groupName;
    private String subjects;
    private String careerInterest;
    private String preferredLocation;
    private String familyIncomeRange;
}
