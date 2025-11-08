package edu.java.lab4.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {
    private Long id;
    private Integer variantNumber;
    private String title;
    private String description;
    private Long examId;
    private String examTitle;
    private LocalDateTime createdAt;
}
