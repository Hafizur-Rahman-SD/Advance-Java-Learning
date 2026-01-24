package bd.edu.seu.jwtsecurity.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        try {
            String jwtError = (String) request.getAttribute("jwt_error");
            String message = switch (jwtError == null ? "" : jwtError) {
                case "TOKEN_EXPIRED" -> "JWT token expired";
                case "TOKEN_INVALID" -> "Invalid JWT token (bad signature/format)";
                default -> "Authentication required";
            };

            response.setStatus(401);
            response.setContentType("application/json");
            mapper.writeValue(response.getOutputStream(), Map.of(
                    "status", 401,
                    "error", "UNAUTHORIZED",
                    "message", message
            ));
        } catch (Exception ignored) {}
    }
}
