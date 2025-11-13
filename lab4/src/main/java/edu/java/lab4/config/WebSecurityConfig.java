package edu.java.lab4.config;

import edu.java.lab4.security.AuthEntryPointJwt;
import edu.java.lab4.security.AuthTokenFilter;
import edu.java.lab4.security.JwtUtil;
import edu.java.lab4.service.impl.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final AuthEntryPointJwt unauthorizedHandler;
    private final JwtUtil jwtUtil;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter(jwtUtil, userDetailsService);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()

                        // ADMIN only endpoints
                        .requestMatchers(HttpMethod.POST, "/api/students").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/students/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/courses").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/courses/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/courses/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/courses/enroll").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/courses/unenroll").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/exams").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/exams/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/exams/tasks").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/exams/grade").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/labworks").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/labworks/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/labworks/grade").hasRole("ADMIN")
                        .requestMatchers("/api/journal/**").hasRole("ADMIN")

                        // STUDENT only endpoints
                        .requestMatchers(HttpMethod.POST, "/api/exams/submit").hasRole("STUDENT")
                        .requestMatchers(HttpMethod.POST, "/api/labworks/submit").hasRole("STUDENT")

                        // Authenticated endpoints (both roles)
                        .requestMatchers(HttpMethod.GET, "/api/courses/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/students/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/exams/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/labworks/**").authenticated()

                        .anyRequest().authenticated()
                );

        // Add JWT filter
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        // For H2 console
        http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

        return http.build();
    }
}