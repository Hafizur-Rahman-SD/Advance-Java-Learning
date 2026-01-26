package bd.edu.seu.shopnopuribackend.modules.match.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchItem {
    private Long uniId;
    private String uni;
    private Long deptId;
    private String dept;
    private String cat;   // REACH / TARGET / SAFE
    private String note;
}
