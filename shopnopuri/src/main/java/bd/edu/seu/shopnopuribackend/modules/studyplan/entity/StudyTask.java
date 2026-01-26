package bd.edu.seu.shopnopuribackend.modules.studyplan.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "study_plan_tasks")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class StudyTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "plan_id", nullable = false)
    private StudyPlan plan;

    @Column(nullable = false)
    private Integer weekNo; // 1..weeks

    @Column(nullable = false)
    private String dayName; // "Mon", "Tue", ...

    @Column(nullable = false)
    private String subject; // "Math", "Physics", etc.

    @Column(nullable = false)
    private String topic;   // "Calculus", "Mechanics"

    @Column(nullable = false)
    private Integer minutes; // planned time

    @Column(nullable = false)
    private Boolean completed;
}
