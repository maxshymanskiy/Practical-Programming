package edu.java.lab4.mapper;

import edu.java.lab4.dto.request.RegisterDto;
import edu.java.lab4.dto.response.AuthDto;
import edu.java.lab4.entity.User;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {

    public User toEntity(RegisterDto dto, String encodedPassword) {
        return User.builder()
                .username(dto.getUsername())
                .password(encodedPassword)
                .role(dto.getRole())
                .build();
    }

    public AuthDto toAuthDto(User user, String token) {
        return AuthDto.builder()
                .token(token)
                .username(user.getUsername())
                .role(user.getRole().name())
                .message(token != null ? "Login successful" : "User registered successfully")
                .build();
    }

    public AuthDto toRegistrationDto(User user) {
        return AuthDto.builder()
                .username(user.getUsername())
                .role(user.getRole().name())
                .message("User registered successfully")
                .build();
    }

    public AuthDto toErrorDto(String message) {
        return AuthDto.builder()
                .message(message)
                .build();
    }
}

