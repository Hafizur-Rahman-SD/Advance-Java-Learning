package bd.edu.seu.shopnopuribackend.modules.studyplan.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "study_plans")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class StudyPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // owner (user email)
    @Column(nullable = false)
    private String studentEmail;

    @Column(nullable = false)
    private String targetName; // e.g. "DU CSE", "BUET CSE", "Medical"

    @Column(nullable = false)
    private Integer weeks; // plan duration

    @Column(nullable = false)
    private Integer hoursPerDay; // student available hours per day

    @Column(nullable = false)
    private Instant createdAt;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<StudyTask> tasks = new ArrayList<>();
}
