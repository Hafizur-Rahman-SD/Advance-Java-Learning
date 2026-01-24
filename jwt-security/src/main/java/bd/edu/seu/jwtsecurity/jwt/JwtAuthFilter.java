package bd.edu.seu.jwtsecurity.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);

        try {
            Claims claims = jwtService.parseAndValidate(token).getPayload();
            String username = claims.getSubject();

            // Load user (or build auth from claims)
            UserDetails user = userDetailsService.loadUserByUsername(username);

            // Optionally use roles from token claims
            Object rolesObj = claims.get("roles");
            List<SimpleGrantedAuthority> auths =
                    (rolesObj instanceof List<?> list)
                            ? list.stream().map(String::valueOf).map(SimpleGrantedAuthority::new).toList()
                            : user.getAuthorities().stream().map(a -> new SimpleGrantedAuthority(a.getAuthority())).toList();

            var authentication = new UsernamePasswordAuthenticationToken(user, null, auths);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            chain.doFilter(request, response);

        } catch (ExpiredJwtException e) {
            // Let EntryPoint handle it (401)
            request.setAttribute("jwt_error", "TOKEN_EXPIRED");
            chain.doFilter(request, response);
        } catch (JwtException e) {
            request.setAttribute("jwt_error", "TOKEN_INVALID");
            chain.doFilter(request, response);
        }
    }
}
