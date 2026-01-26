package bd.edu.seu.shopnopuribackend.modules.Scholarship.dto;



import bd.edu.seu.shopnopuribackend.modules.Scholarship.entity.SchLvl;
import bd.edu.seu.shopnopuribackend.modules.Scholarship.entity.SchType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class SchReqC {

    @NotBlank
    private String title;

    private String des;

    @NotNull
    private SchLvl lvl;

    @NotNull
    private SchType type;

    @NotBlank
    private String country;

    private String provider;

    private Double minGpa;
    private Integer minIncomeBdt;

    private String url;
    private Instant deadlineAt;

    private Boolean act; // optional, default true in service
}
