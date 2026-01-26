package bd.edu.seu.shopnopuribackend.modules.Scholarship.dto;


import bd.edu.seu.shopnopuribackend.modules.Scholarship.entity.SchLvl;
import bd.edu.seu.shopnopuribackend.modules.Scholarship.entity.SchType;
import lombok.*;

import java.time.Instant;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class SchRes {
    private Long id;
    private String title;
    private String des;
    private SchLvl lvl;
    private SchType type;
    private String country;
    private String provider;
    private Double minGpa;
    private Integer minIncomeBdt;
    private String url;
    private Instant deadlineAt;
    private boolean act;
}

