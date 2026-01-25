package bd.edu.seu.shopnopuribackend.modules.auth.dto;

public class AuthResponse {

    private Long userId;
    private String email;
    private String role;
    private String message;
    private String token;

    public AuthResponse(Long userId, String email, String role, String message, String token) {
        this.userId = userId;
        this.email = email;
        this.role = role;
        this.message = message;
        this.token = token;
    }

    public Long getUserId() { return userId; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public String getMessage() { return message; }
    public String getToken() { return token; }
}
