package edu.java.lab4.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LabWorkDto {
    private Long id;
    private Integer labNumber;
    private String title;
    private String description;
    private LocalDateTime deadline;
    private Boolean allowsLateSubmission;
    private Double latePenaltyPerDay;
    private Integer maxLateDays;
    private Long courseId;
    private String courseName;
    private Integer submissionsCount;
    private LocalDateTime createdAt;
}