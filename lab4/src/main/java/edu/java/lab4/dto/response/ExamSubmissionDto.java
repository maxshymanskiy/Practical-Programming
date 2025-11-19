package edu.java.lab4.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamSubmissionDto {
    private Long id;
    private Long studentId;
    private String studentName;
    private Long examId;
    private String examTitle;
    private Long assignedTaskId;
    private Integer taskVariant;
    private String submissionUrl;
    private String answer;
    private LocalDateTime submittedAt;
    private Double grade;
    private LocalDateTime gradedAt;
    private String graderNotes;
}
