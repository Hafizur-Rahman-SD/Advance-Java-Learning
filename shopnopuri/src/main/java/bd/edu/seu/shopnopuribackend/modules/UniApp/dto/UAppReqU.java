package bd.edu.seu.shopnopuribackend.modules.UniApp.dto;

import bd.edu.seu.shopnopuribackend.modules.UniApp.entity.UAppSt;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class UAppReqU {

    private Long uniId;

    @Size(max = 120, message = "uniName too long")
    private String uniName;

    private Long depId;

    @Size(max = 120, message = "depName too long")
    private String depName;

    @Min(value = 2000, message = "year invalid")
    @Max(value = 2100, message = "year invalid")
    private Integer year;

    @Size(max = 40, message = "season too long")
    private String season;

    private UAppSt st;

    private LocalDate examDate;
    private LocalDate vivaDate;

    @Size(max = 2000, message = "note too long")
    private String note;
}
