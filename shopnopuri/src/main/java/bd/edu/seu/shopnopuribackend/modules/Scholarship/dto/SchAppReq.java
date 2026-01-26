package bd.edu.seu.shopnopuribackend.modules.Scholarship.dto;


import bd.edu.seu.shopnopuribackend.modules.Scholarship.entity.AppSt;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class SchAppReq {

    @NotNull
    private AppSt st;

    private String note;
}
