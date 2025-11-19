package edu.java.lab4.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {
    private Long id;
    private String name;
    private String description;
    private String academicYear;
    private Integer labWeight;
    private Integer labCount;
    private Integer examWeight;
    private Integer maxGrade; // Calculated field
    private Integer enrolledStudents; // Count
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
