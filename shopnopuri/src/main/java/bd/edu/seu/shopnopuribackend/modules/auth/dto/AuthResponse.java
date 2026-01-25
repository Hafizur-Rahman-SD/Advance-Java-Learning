package bd.edu.seu.shopnopuribackend.modules.auth.dto;



//No tocken just confermation login or not
public class AuthResponse {

    private Long userId;
    private String email;
    private String role;
    private String message;

    public AuthResponse(Long userId, String email, String role, String message) {
        this.userId = userId;
        this.email = email;
        this.role = role;
        this.message = message;
    }

    public Long getUserId() { return userId; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public String getMessage() { return message; }
}
