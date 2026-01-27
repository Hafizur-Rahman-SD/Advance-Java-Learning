package bd.edu.seu.shopnopuribackend.modules.UniApp.dto;

import bd.edu.seu.shopnopuribackend.modules.UniApp.entity.UAppSt;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class UAppRes {
    private Long id;

    private Long uniId;
    private String uniName;

    private Long depId;
    private String depName;

    private Integer year;
    private String season;

    private UAppSt st;

    private LocalDate examDate;
    private LocalDate vivaDate;

    private String note;

    private Instant createdAt;
    private Instant updatedAt;
}
