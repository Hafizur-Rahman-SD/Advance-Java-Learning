package bd.edu.seu.shopnopuribackend.config.security;

import bd.edu.seu.shopnopuribackend.modules.auth.security.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable());
        http.cors(Customizer.withDefaults());
        http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests(auth -> auth

                // ===== PUBLIC =====
                .requestMatchers("/", "/health").permitAll()

                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll()

                // ===== CAREER TEST (Student only) =====
                // Controller: /api/career-test/questions, /api/career-test/submit
                .requestMatchers("/api/career-test", "/api/career-test/**").hasRole("STUDENT")

                // ===== ME strict (Student only) =====
                .requestMatchers("/api/me/profile/**").hasRole("STUDENT")
                .requestMatchers("/api/me/career-result", "/api/me/career-result/**").hasRole("STUDENT")
                .requestMatchers("/api/me/match", "/api/me/match/**").hasRole("STUDENT")
                .requestMatchers("/api/me/pred", "/api/me/pred/**").hasRole("STUDENT")

                // ===== ME general (future-safe) =====
                // if you add more /api/me endpoints later, they won't randomly 403
                .requestMatchers("/api/me/**").hasAnyRole("STUDENT", "PARENT", "ADMIN")

                // ===== MATCH (Student + Admin) =====
                .requestMatchers("/api/match", "/api/match/**").hasAnyRole("STUDENT", "ADMIN")

                // ===== MATCHMAKER (Student + Admin) =====
                .requestMatchers("/api/matchmaker", "/api/matchmaker/**").hasAnyRole("STUDENT", "ADMIN")

                // ===== PRED (Student + Admin) =====
                // Controller: POST /api/pred
                .requestMatchers("/api/pred", "/api/pred/**").hasAnyRole("STUDENT", "ADMIN")

                // Old prediction module (if exists)
                .requestMatchers("/api/prediction", "/api/prediction/**").hasAnyRole("STUDENT", "ADMIN")

                // ===== DEBUG =====
                .requestMatchers("/api/test/**").authenticated()

                // ===== UNIVERSITY + DEPARTMENT =====
                // WRITE: ADMIN only
                .requestMatchers(HttpMethod.POST, "/api/universities/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT,  "/api/universities/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/api/universities/**").hasRole("ADMIN")

                .requestMatchers(HttpMethod.POST, "/api/departments/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT,  "/api/departments/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/api/departments/**").hasRole("ADMIN")

                // READ: authenticated (keep your previous behavior)
                .requestMatchers(HttpMethod.GET, "/api/universities/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/api/departments/**").authenticated()

                // ===== EVERYTHING ELSE =====
                .anyRequest().authenticated()
        );

        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
