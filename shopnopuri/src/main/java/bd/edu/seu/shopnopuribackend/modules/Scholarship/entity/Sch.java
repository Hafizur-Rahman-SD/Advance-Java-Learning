package bd.edu.seu.shopnopuribackend.modules.Scholarship.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "sch")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Sch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 4000)
    private String des;

    @Enumerated(EnumType.STRING)
    private SchLvl lvl;

    @Enumerated(EnumType.STRING)
    private SchType type;

    private String country;
    private String provider;

    private Double minGpa;
    private Integer minIncomeBdt;

    private String url;

    private Instant deadlineAt;

    @Column(nullable = false)
    private boolean act;   // 🔑 THIS FIELD

    private Instant createdAt;

    @PrePersist
    void prePersist() {
        if (createdAt == null) createdAt = Instant.now();
    }



    // --- Finder fields (NEW) ---
    private String category;     // MERIT/NEED/STEM etc
    private String district;     // Dhaka etc
    private String university;   // SEU/DU etc
    private String gender;       // MALE/FEMALE/OTHER (null => all)

    private Double maxGpa;       // optional
    private Integer maxFamilyIncomeBdt; // optional

}
