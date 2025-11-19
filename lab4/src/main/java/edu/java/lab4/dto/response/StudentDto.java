package edu.java.lab4.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private String studentNumber;
    private Integer enrolledCoursesCount;
    private LocalDateTime createdAt;
}
