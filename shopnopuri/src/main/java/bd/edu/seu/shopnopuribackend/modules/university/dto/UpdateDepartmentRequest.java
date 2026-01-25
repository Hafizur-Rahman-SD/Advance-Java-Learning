package bd.edu.seu.shopnopuribackend.modules.university.dto;

import jakarta.validation.constraints.NotBlank;

public class UpdateDepartmentRequest {

    @NotBlank
    private String name;

    private String code;
    private String groupName;
    private String admissionRequirements;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getGroupName() { return groupName; }
    public void setGroupName(String groupName) { this.groupName = groupName; }

    public String getAdmissionRequirements() { return admissionRequirements; }
    public void setAdmissionRequirements(String admissionRequirements) { this.admissionRequirements = admissionRequirements; }
}
