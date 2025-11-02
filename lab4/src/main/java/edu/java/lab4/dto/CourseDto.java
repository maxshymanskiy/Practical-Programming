package edu.java.lab4.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {
    @NotBlank(message = "Course ID cannot be blank")
    private String courseId;

    @NotBlank(message = "Course name cannot be blank")
    private String courseName;

    @PositiveOrZero(message = "Lab points must be non-negative")
    private int maxTotalLabPoints;

    @PositiveOrZero(message = "Exam points must be non-negative")
    private int maxTotalExamPoints;
}
