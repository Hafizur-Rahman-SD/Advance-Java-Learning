package bd.edu.seu.shopnopuribackend.modules.profile.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ProfileUpsertRequest {

    @NotBlank
    private String fullName;

    private String phone;

    private BigDecimal hscGpa;
    private BigDecimal sscGpa;

    private String board;

    @NotNull
    private String groupName;

    private String subjectsJson;
    private String careerInterest;
    private String preferredLocation;
    private String familyIncomeRange;

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
