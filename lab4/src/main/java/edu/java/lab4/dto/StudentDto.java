package edu.java.lab4.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    @NotBlank(message = "Student ID cannot be blank")
    private String studentId;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Email(message = "Must be a valid email address")
    @NotBlank(message = "Email cannot be blank")
    private String email;
}
