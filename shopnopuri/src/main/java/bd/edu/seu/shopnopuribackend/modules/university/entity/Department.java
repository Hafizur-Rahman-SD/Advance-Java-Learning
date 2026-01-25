package bd.edu.seu.shopnopuribackend.modules.university.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "departments",
        uniqueConstraints = @UniqueConstraint(columnNames = {"university_id", "name"}))
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id", nullable = false)
    private University university;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(length = 30)
    private String code; // CSE, EEE, etc.

    @Column(length = 30)
    private String groupName; // SCIENCE/COMMERCE/ARTS

    @Column(columnDefinition = "text")
    private String admissionRequirements;

    public Long getId() { return id; }

    public University getUniversity() { return university; }
    public void setUniversity(University university) { this.university = university; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getGroupName() { return groupName; }
    public void setGroupName(String groupName) { this.groupName = groupName; }

    public String getAdmissionRequirements() { return admissionRequirements; }
    public void setAdmissionRequirements(String admissionRequirements) { this.admissionRequirements = admissionRequirements; }
}
