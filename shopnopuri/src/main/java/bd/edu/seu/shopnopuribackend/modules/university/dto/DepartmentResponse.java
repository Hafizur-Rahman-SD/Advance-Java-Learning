package bd.edu.seu.shopnopuribackend.modules.university.dto;

public class DepartmentResponse {

    private Long id;
    private String name;
    private String code;
    private String groupName;
    private String admissionRequirements;

    public DepartmentResponse(Long id, String name, String code,
                              String groupName, String admissionRequirements) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.groupName = groupName;
        this.admissionRequirements = admissionRequirements;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCode() { return code; }
    public String getGroupName() { return groupName; }
    public String getAdmissionRequirements() { return admissionRequirements; }
}
