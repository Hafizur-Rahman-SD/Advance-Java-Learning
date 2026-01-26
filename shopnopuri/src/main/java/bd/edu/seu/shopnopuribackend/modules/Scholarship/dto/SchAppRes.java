package bd.edu.seu.shopnopuribackend.modules.Scholarship.dto;


import bd.edu.seu.shopnopuribackend.modules.Scholarship.entity.AppSt;
import lombok.*;

import java.time.Instant;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class SchAppRes {
    private Long schId;
    private String title;
    private AppSt st;
    private String note;
    private Instant appliedAt;
}
