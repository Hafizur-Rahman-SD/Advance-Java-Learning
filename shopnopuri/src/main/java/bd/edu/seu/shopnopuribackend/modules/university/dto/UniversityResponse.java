package bd.edu.seu.shopnopuribackend.modules.university.dto;

public class UniversityResponse {

    private Long id;
    private String name;
    private String type;
    private String city;
    private String website;
    private String description;

    public UniversityResponse(Long id, String name, String type,
                              String city, String website, String description) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.city = city;
        this.website = website;
        this.description = description;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public String getCity() { return city; }
    public String getWebsite() { return website; }
    public String getDescription() { return description; }
}
