package bd.edu.seu.shopnopuribackend.modules.career.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "career_test_answers",
        uniqueConstraints = @UniqueConstraint(name = "uq_attempt_question", columnNames = {"attempt_id", "question_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CareerTestAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="attempt_id", nullable = false)
    private CareerTestAttempt attempt;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="question_id", nullable = false)
    private CareerQuestion question;

    // Likert scale: 0..4
    @Column(nullable = false)
    private int value;
}
