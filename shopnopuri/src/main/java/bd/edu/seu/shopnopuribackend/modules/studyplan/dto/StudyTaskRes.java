package bd.edu.seu.shopnopuribackend.modules.studyplan.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class StudyTaskRes {
    private Long id;
    private Integer weekNo;
    private String dayName;
    private String subject;
    private String topic;
    private Integer minutes;
    private Boolean completed;
}
