package edu.java.lab4.service.impl;

import edu.java.lab4.dto.request.LoginDto;
import edu.java.lab4.dto.request.RegisterDto;
import edu.java.lab4.dto.response.AuthDto;
import edu.java.lab4.entity.Student;
import edu.java.lab4.entity.User;
import edu.java.lab4.entity.UserRole;
import edu.java.lab4.exception.DuplicateEntityException;
import edu.java.lab4.exception.ResourceNotFoundException;
import edu.java.lab4.mapper.AuthMapper;
import edu.java.lab4.repository.StudentRepository;
import edu.java.lab4.repository.UserRepository;
import edu.java.lab4.security.JwtUtil;
import edu.java.lab4.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthMapper authMapper;

    @Override
    public AuthDto login(LoginDto request) {
        log.info("Attempting login for user: {}", request.getUsername());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userDetails.getUsername());

        User user = userRepository.findByUsername(request.getUsername());

        log.info("User {} logged in successfully", request.getUsername());
        return authMapper.toAuthDto(user, token);
    }

    @Override
    @Transactional
    public AuthDto register(RegisterDto request) {
        log.info("Registering new user: {} with role: {}", request.getUsername(), request.getRole());

        if (userRepository.existsByUsername(request.getUsername())) {
            log.warn("Registration failed: username {} already exists", request.getUsername());
            throw new DuplicateEntityException("User", "username", request.getUsername());
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user = authMapper.toEntity(request, encodedPassword);
        userRepository.save(user);

        if (request.getRole() == UserRole.STUDENT && request.getStudentId() != null) {
            linkUserToStudent(user, request.getStudentId());
        }

        log.info("User {} registered successfully", user.getUsername());
        return authMapper.toRegistrationDto(user);
    }

    private void linkUserToStudent(User user, Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
        student.setUserId(user.getId());
        studentRepository.save(student);
        log.info("Linked user {} to student {}", user.getUsername(), student.getStudentNumber());
    }
}

