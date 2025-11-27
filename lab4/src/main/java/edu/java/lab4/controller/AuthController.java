package edu.java.lab4.controller;

import edu.java.lab4.dto.request.LoginDto;
import edu.java.lab4.dto.request.RegisterDto;
import edu.java.lab4.dto.response.AuthDto;
import edu.java.lab4.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthDto> login(@Valid @RequestBody LoginDto request) {
        log.info("REST: Login request for user: {}", request.getUsername());
        AuthDto response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AuthDto> register(@Valid @RequestBody RegisterDto request) {
        log.info("REST: Register request for username: {} with role: {}", request.getUsername(), request.getRole());
        AuthDto response = authService.register(request);

        if (response.getToken() == null && "Username already exists".equals(response.getMessage())) {
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}