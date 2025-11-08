package edu.java.lab4.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private Integer status;
    private String error;
    private String message;
    private String path;
    private LocalDateTime timestamp;
    private List<String> validationErrors; // For validation failures
}
