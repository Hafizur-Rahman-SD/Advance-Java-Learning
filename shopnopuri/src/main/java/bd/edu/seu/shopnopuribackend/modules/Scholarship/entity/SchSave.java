package bd.edu.seu.shopnopuribackend.modules.Scholarship.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(
        name = "sch_save",
        uniqueConstraints = @UniqueConstraint(columnNames = {"stu_email", "sch_id"})
)
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class SchSave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stu_email", nullable = false)
    private String stuEmail;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sch_id", nullable = false)
    private Sch sch;

    @Column(nullable = false, updatable = false)
    private Instant savedAt;

    @PrePersist
    void prePersist() {
        if (savedAt == null) savedAt = Instant.now();
    }
}
