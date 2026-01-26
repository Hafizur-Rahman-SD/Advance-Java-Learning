package bd.edu.seu.shopnopuribackend.modules.studyplan.dto;

import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class StudyPlanRes {
    private Long id;
    private String studentEmail;
    private String targetName;
    private Integer weeks;
    private Integer hoursPerDay;
    private Instant createdAt;
    private Integer completionPercent;
    private List<StudyTaskRes> tasks;
}
