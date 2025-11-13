package edu.java.lab4.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static edu.java.lab4.constant.GradingConstants.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseCreateRequest {

    @NotBlank(message = "Course name is required")
    @Size(min = MIN_COURSE_NAME_LENGTH,
          max = MAX_COURSE_NAME_LENGTH,
          message = "Course name must be between 3 and 100 characters"
    )
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @NotBlank(message = "Academic year is required")
    @Pattern(regexp = "\\d{4}-\\d{4}", message = "Academic year must be in format YYYY-YYYY")
    private String academicYear;

    // Optional grading formula (uses defaults if not provided)
    @Min(value = 1, message = "Lab weight must be at least 1")
    @Max(value = 50, message = "Lab weight cannot exceed 50")
    private Integer labWeight;

    @Min(value = 1, message = "Lab count must be at least 1")
    @Max(value = 20, message = "Lab count cannot exceed 20")
    private Integer labCount;

    @Min(value = 1, message = "Exam weight must be at least 1")
    @Max(value = 100, message = "Exam weight cannot exceed 100")
    private Integer examWeight;

    // Apply defaults if not provided
    public Integer getLabWeight() {
        return labWeight != null ? labWeight : DEFAULT_LAB_WEIGHT;
    }

    public Integer getLabCount() {
        return labCount != null ? labCount : DEFAULT_LAB_COUNT;
    }

    public Integer getExamWeight() {
        return examWeight != null ? examWeight : DEFAULT_EXAM_WEIGHT;
    }
}