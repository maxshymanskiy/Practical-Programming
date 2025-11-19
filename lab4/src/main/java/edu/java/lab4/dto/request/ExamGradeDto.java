package edu.java.lab4.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamGradeDto {

    @NotNull(message = "Submission ID is required")
    private Long submissionId;

    @NotNull(message = "Grade is required")
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "100.0")
    private Double grade;

    @Size(max = 1000)
    private String graderNotes;
}
