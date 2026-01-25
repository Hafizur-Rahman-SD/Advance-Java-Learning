package bd.edu.seu.shopnopuribackend.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Disable CSRF for REST APIs (important for Postman)
        http.csrf(AbstractHttpConfigurer::disable);

        // Allow swagger + health + auth endpoints without login
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/health").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/auth/**").permitAll()
                .anyRequest().authenticated()
        );


        // Disable basic login popup
        http.httpBasic(AbstractHttpConfigurer::disable);
        http.formLogin(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
