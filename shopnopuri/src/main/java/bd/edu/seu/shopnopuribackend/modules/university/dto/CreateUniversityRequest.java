package bd.edu.seu.shopnopuribackend.modules.university.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateUniversityRequest {

    @NotBlank
    private String name;

    private String type;
    private String city;
    private String website;
    private String description;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
