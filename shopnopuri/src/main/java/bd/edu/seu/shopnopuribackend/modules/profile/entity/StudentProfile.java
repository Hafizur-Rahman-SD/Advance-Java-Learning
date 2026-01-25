package bd.edu.seu.shopnopuribackend.modules.profile.entity;

import bd.edu.seu.shopnopuribackend.modules.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "student_profiles")
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Each STUDENT user can have only one profile
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "full_name", nullable = false, length = 120)
    private String fullName;

    @Column(length = 20)
    private String phone;

    // DECIMAL(3,2) => 0.00 to 9.99 (GPA use-case)
    @Column(name = "ssc_gpa", precision = 3, scale = 2)
    private BigDecimal sscGpa;

    @Column(name = "hsc_gpa", precision = 3, scale = 2)
    private BigDecimal hscGpa;

    @Column(length = 30)
    private String board; // DHAKA, CHITTAGONG etc.

    @Column(name = "group_name", length = 20)
    private String groupName; // SCIENCE, COMMERCE, ARTS

    // For v1 we store as comma-separated string
    @Column(length = 500)
    private String subjects; // Physics,Chemistry,Math,ICT

    @Column(name = "career_interest", length = 50)
    private String careerInterest; // SOFTWARE_ENGINEER etc.

    @Column(name = "preferred_location", length = 50)
    private String preferredLocation; // DHAKA etc.

    @Column(name = "family_income_range", length = 50)
    private String familyIncomeRange; // 50000-100000

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @PrePersist
    public void prePersist() {
        Instant now = Instant.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = Instant.now();
    }
}
