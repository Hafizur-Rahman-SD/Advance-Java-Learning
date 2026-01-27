package bd.edu.seu.shopnopuribackend.modules.Scholarship.dto;

import bd.edu.seu.shopnopuribackend.modules.Scholarship.entity.SchLvl;
import bd.edu.seu.shopnopuribackend.modules.Scholarship.entity.SchType;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.Instant;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class SchReqU {

    @Size(max = 255, message = "title too long")
    private String title;

    @Size(max = 4000, message = "des too long")
    private String des;

    private SchLvl lvl;
    private SchType type;

    @Size(max = 80, message = "country too long")
    private String country;

    @Size(max = 120, message = "provider too long")
    private String provider;

    @DecimalMin(value = "0.0", message = "minGpa must be >= 0")
    @DecimalMax(value = "5.0", message = "minGpa must be <= 5")
    private Double minGpa;

    @Min(value = 0, message = "minIncomeBdt must be >= 0")
    private Integer minIncomeBdt;

    @Size(max = 500, message = "url too long")
    private String url;

    private Instant deadlineAt;

    private Boolean act;



    private String category;
    private String district;
    private String university;
    private String gender;

    private Double maxGpa;
    private Integer maxFamilyIncomeBdt;

}
