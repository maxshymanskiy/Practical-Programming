package edu.java.lab4.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LabSubmissionDto {
    private Long id;
    private Long studentId;
    private String studentName;
    private Long labWorkId;
    private String labTitle;
    private String submissionUrl;
    private String notes;
    private LocalDateTime submittedAt;
    private Boolean isLate;
    private Double rawGrade;
    private Double finalGrade;
    private Double penaltyApplied;
    private LocalDateTime gradedAt;
    private String graderNotes;
}
