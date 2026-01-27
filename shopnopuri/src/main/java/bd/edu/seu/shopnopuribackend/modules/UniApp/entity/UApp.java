package bd.edu.seu.shopnopuribackend.modules.UniApp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "u_app")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class UApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String stuEmail;

    // keep simple (no FK relation now)
    private Long uniId;
    private String uniName;

    private Long depId;
    private String depName;

    // intake/session info
    private Integer year;      // e.g. 2026
    private String season;     // e.g. "Spring" / "Fall" / "Admission-2026"

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UAppSt st;

    private LocalDate examDate;
    private LocalDate vivaDate;

    @Column(length = 2000)
    private String note;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    @PrePersist
    void prePersist() {
        Instant now = Instant.now();
        if (createdAt == null) createdAt = now;
        updatedAt = now;
        if (st == null) st = UAppSt.PLANNING;
    }

    @PreUpdate
    void preUpdate() {
        updatedAt = Instant.now();
    }
}
