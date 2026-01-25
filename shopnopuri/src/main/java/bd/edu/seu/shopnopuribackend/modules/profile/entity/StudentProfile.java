package bd.edu.seu.shopnopuribackend.modules.profile.entity;

import bd.edu.seu.shopnopuribackend.modules.user.entity.User;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "student_profiles")
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "full_name", nullable = false, length = 120)
    private String fullName;

    @Column(length = 20)
    private String phone;

    // Matches MySQL DECIMAL(3,2)
    @Column(name = "hsc_gpa", precision = 3, scale = 2)
    private BigDecimal hscGpa;

    // Matches MySQL DECIMAL(3,2)
    @Column(name = "ssc_gpa", precision = 3, scale = 2)
    private BigDecimal sscGpa;

    @Column(length = 50)
    private String board;

    // Avoid reserved keyword problems by using group_name column in DB
    @Column(name = "group_name", nullable = false, length = 20)
    private String groupName;

    @Column(name = "subjects_json", columnDefinition = "json")
    private String subjectsJson;

    @Column(name = "career_interest", length = 100)
    private String careerInterest;

    @Column(name = "preferred_location", length = 50)
    private String preferredLocation;

    @Column(name = "family_income_range", length = 50)
    private String familyIncomeRange;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
    }

    public Long getId() { return id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public BigDecimal getHscGpa() { return hscGpa; }
    public void setHscGpa(BigDecimal hscGpa) { this.hscGpa = hscGpa; }

    public BigDecimal getSscGpa() { return sscGpa; }
    public void setSscGpa(BigDecimal sscGpa) { this.sscGpa = sscGpa; }

    public String getBoard() { return board; }
    public void setBoard(String board) { this.board = board; }

    public String getGroupName() { return groupName; }
    public void setGroupName(String groupName) { this.groupName = groupName; }

    public String getSubjectsJson() { return subjectsJson; }
    public void setSubjectsJson(String subjectsJson) { this.subjectsJson = subjectsJson; }

    public String getCareerInterest() { return careerInterest; }
    public void setCareerInterest(String careerInterest) { this.careerInterest = careerInterest; }

    public String getPreferredLocation() { return preferredLocation; }
    public void setPreferredLocation(String preferredLocation) { this.preferredLocation = preferredLocation; }

    public String getFamilyIncomeRange() { return familyIncomeRange; }
    public void setFamilyIncomeRange(String familyIncomeRange) { this.familyIncomeRange = familyIncomeRange; }
}
