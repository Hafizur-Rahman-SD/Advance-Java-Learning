package bd.edu.seu.shopnopuribackend.modules.Scholarship.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(
        name = "sch_app",
        uniqueConstraints = @UniqueConstraint(columnNames = {"stu_email", "sch_id"})
)
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class SchApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stu_email", nullable = false)
    private String stuEmail;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sch_id", nullable = false)
    private Sch sch;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppSt st;

    @Column(length = 2000)
    private String note;

    private Instant appliedAt;

    @PrePersist
    void prePersist() {
        if (appliedAt == null) appliedAt = Instant.now();
        if (st == null) st = AppSt.PLANNING;
    }
}
