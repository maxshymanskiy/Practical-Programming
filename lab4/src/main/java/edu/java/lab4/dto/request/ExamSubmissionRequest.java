package edu.java.lab4.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamSubmissionRequest {

    @NotNull(message = "Student ID is required")
    private Long studentId;

    @NotNull(message = "Exam ID is required")
    private Long examId;

    private Long assignedTaskId; // Optional: auto-assign if null

    @Size(max = 500)
    private String submissionUrl;

    @Size(max = 1000)
    private String answer;
}
