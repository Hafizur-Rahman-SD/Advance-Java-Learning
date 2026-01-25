package bd.edu.seu.shopnopuribackend.modules.profile.dto;

import java.math.BigDecimal;

public class ProfileResponse {

    private Long id;
    private String email;
    private String fullName;
    private String phone;
    private BigDecimal hscGpa;
    private BigDecimal sscGpa;
    private String board;
    private String groupName;
    private String subjectsJson;
    private String careerInterest;
    private String preferredLocation;
    private String familyIncomeRange;

    public ProfileResponse(Long id, String email, String fullName, String phone,
                           BigDecimal hscGpa, BigDecimal sscGpa, String board, String groupName,
                           String subjectsJson, String careerInterest, String preferredLocation,
                           String familyIncomeRange) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.phone = phone;
        this.hscGpa = hscGpa;
        this.sscGpa = sscGpa;
        this.board = board;
        this.groupName = groupName;
        this.subjectsJson = subjectsJson;
        this.careerInterest = careerInterest;
        this.preferredLocation = preferredLocation;
        this.familyIncomeRange = familyIncomeRange;
    }

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getFullName() { return fullName; }
    public String getPhone() { return phone; }
    public BigDecimal getHscGpa() { return hscGpa; }
    public BigDecimal getSscGpa() { return sscGpa; }
    public String getBoard() { return board; }
    public String getGroupName() { return groupName; }
    public String getSubjectsJson() { return subjectsJson; }
    public String getCareerInterest() { return careerInterest; }
    public String getPreferredLocation() { return preferredLocation; }
    public String getFamilyIncomeRange() { return familyIncomeRange; }
}
