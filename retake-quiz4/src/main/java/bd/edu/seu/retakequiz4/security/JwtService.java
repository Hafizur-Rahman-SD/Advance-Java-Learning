package bd.edu.seu.retakequiz4.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;

@Service
public class JwtService {

    @Value("${app.jwt.secret}")
    private String secret;

    @Value("${app.jwt.expirationMinutes}")
    private long expirationMinutes;

    public String generateToken(String username, Set<String> roles) {
        long now = System.currentTimeMillis();
        long exp = now + (expirationMinutes * 60 * 1000);

        return Jwts.builder()
                .subject(username)
                .claim("roles", roles)
                .issuedAt(new Date(now))
                .expiration(new Date(exp))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    public Claims parse(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getUsername(String token) {
        return parse(token).getSubject();
    }
}
