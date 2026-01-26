package bd.edu.seu.shopnopuribackend.modules.career.entity;

import bd.edu.seu.shopnopuribackend.modules.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "career_test_attempts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CareerTestAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many attempts for one user
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @Column(name="submitted_at", nullable = false, updatable = false)
    private Instant submittedAt;

    // Store top careers + match% + dimension scores as JSON string
    @Lob
    @Column(name="result_json", nullable = false, columnDefinition = "TEXT")
    private String resultJson;

    @PrePersist
    void onSubmit() {
        if (submittedAt == null) submittedAt = Instant.now();
    }
}
