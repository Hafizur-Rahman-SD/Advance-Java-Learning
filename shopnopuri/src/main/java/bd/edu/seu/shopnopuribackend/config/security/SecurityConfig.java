package bd.edu.seu.shopnopuribackend.config.security;

import bd.edu.seu.shopnopuribackend.modules.auth.security.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
        http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests(auth -> auth
                // public
                .requestMatchers("/", "/health").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
                .requestMatchers("/api/me/profile/**").hasRole("STUDENT")
                .requestMatchers("/api/prediction/**").hasAnyRole("STUDENT", "ADMIN")


                //for carrear security 
                .requestMatchers("/api/career-test/**").hasRole("STUDENT")
                .requestMatchers("/api/me/career-result").hasRole("STUDENT")




                // authenticated university endpoints (GET/POST/PUT/DELETE )
                .requestMatchers("/api/universities/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/api/departments/**").authenticated()

                // WRITE: ADMIN only
                .requestMatchers(HttpMethod.POST, "/api/universities/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT,  "/api/universities/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/api/universities/**").hasRole("ADMIN")

                .requestMatchers(HttpMethod.POST, "/api/departments/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT,  "/api/departments/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/api/departments/**").hasRole("ADMIN")


                // everything else authenticated
                .anyRequest().authenticated()
        );

        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
