package edu.java.lab4.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabGradeRequest {

    @NotNull(message = "Submission ID is required")
    private Long submissionId;

    @NotNull(message = "Grade is required")
    @DecimalMin(value = "0.0", message = "Grade cannot be negative")
    @DecimalMax(value = "100.0", message = "Grade cannot exceed 100")
    private Double grade;

    @Size(max = 1000)
    private String graderNotes;
}
