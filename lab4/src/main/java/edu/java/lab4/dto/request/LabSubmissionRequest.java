package edu.java.lab4.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabSubmissionRequest {

    @NotNull(message = "Student ID is required")
    private Long studentId;

    @NotNull(message = "Lab work ID is required")
    private Long labWorkId;

    @Size(max = 500)
    private String submissionUrl;

    @Size(max = 1000)
    private String notes;
}
