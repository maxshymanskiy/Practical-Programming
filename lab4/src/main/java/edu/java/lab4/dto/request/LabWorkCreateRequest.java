package edu.java.lab4.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

import static edu.java.lab4.constant.GradingConstants.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabWorkCreateRequest {

    @NotNull(message = "Course ID is required")
    private Long courseId;

    @NotNull(message = "Lab number is required")
    @Min(value = 1, message = "Lab number must be at least 1")
    private Integer labNumber;

    @NotBlank(message = "Title is required")
    @Size(min = 5, max = 200)
    private String title;

    @Size(min = MIN_COURSE_NAME_LENGTH,
            max = MAX_TASK_DESCRIPTION_LENGTH)
    private String description;

    @NotNull(message = "Deadline is required")
    @Future(message = "Deadline must be in the future")
    private LocalDateTime deadline;

    @Builder.Default
    private Boolean allowsLateSubmission = true;

    @DecimalMin(value = "0.0", message = "Penalty cannot be negative")
    @DecimalMax(value = "1.0", message = "Penalty cannot exceed 100%")
    private Double latePenaltyPerDay;

    @Min(value = 1, message = "Max late days must be at least 1")
    @Max(value = MAX_LATE_DAYS)
    private Integer maxLateDays;
}
