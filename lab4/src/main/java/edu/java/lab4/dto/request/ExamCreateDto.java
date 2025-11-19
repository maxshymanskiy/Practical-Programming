package edu.java.lab4.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamCreateDto {

    @NotNull(message = "Course ID is required")
    private Long courseId;

    @NotBlank(message = "Title is required")
    @Size(min = 5, max = 200)
    private String title;

    @Size(max = 1000)
    private String description;

    @NotNull(message = "Scheduled date is required")
    @Future(message = "Exam must be scheduled in the future")
    private LocalDateTime scheduledDate;

    @Min(value = 10, message = "Duration must be at least 10 minutes")
    @Max(value = 300, message = "Duration cannot exceed 300 minutes")
    private Integer durationMinutes;
}
