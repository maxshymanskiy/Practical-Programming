package edu.java.lab4.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDetailResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String studentNumber;
    private List<CourseDto> courses;
    private LocalDateTime createdAt;
}
