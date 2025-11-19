package edu.java.lab4.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamDto {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime scheduledDate;
    private Integer durationMinutes;
    private Long courseId;
    private String courseName;
    private Integer tasksCount;
    private Integer submissionsCount;
    private LocalDateTime createdAt;
}
