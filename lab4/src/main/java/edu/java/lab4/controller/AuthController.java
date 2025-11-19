package edu.java.lab4.controller;

import edu.java.lab4.dto.request.LoginDto;
import edu.java.lab4.dto.request.RegisterDto;
import edu.java.lab4.dto.response.AuthDto;
import edu.java.lab4.entity.Student;
import edu.java.lab4.entity.User;
import edu.java.lab4.entity.UserRole;
import edu.java.lab4.exception.ResourceNotFoundException;
import edu.java.lab4.repository.StudentRepository;
import edu.java.lab4.repository.UserRepository;
import edu.java.lab4.security.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<AuthDto> login(@Valid @RequestBody LoginDto request) {
        log.info("Login attempt for user: {}", request.getUsername());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userDetails.getUsername());

        User user = userRepository.findByUsername(request.getUsername());

        log.info("User {} logged in successfully", request.getUsername());

        return ResponseEntity.ok(AuthDto.builder()
                .token(token)
                .username(user.getUsername())
                .role(user.getRole().name())
                .message("Login successful")
                .build());
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AuthDto> register(@Valid @RequestBody RegisterDto request) {
        log.info("Register attempt for username: {} with role: {}", request.getUsername(), request.getRole());

        if (userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest()
                    .body(AuthDto.builder()
                            .message("Username already exists")
                            .build());
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        user = userRepository.save(user);

        // Link to a student if the role is STUDENT and studentId provided
        if (request.getRole() == UserRole.STUDENT && request.getStudentId() != null) {
            Student student = studentRepository.findById(request.getStudentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Student", "id", request.getStudentId()));
            student.setUserId(user.getId());
            studentRepository.save(student);
            log.info("Linked user {} to student {}", user.getUsername(), student.getStudentNumber());
        }

        log.info("User {} registered successfully", user.getUsername());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AuthDto.builder()
                        .username(user.getUsername())
                        .role(user.getRole().name())
                        .message("User registered successfully")
                        .build());
    }
}