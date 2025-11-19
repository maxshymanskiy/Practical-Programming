package edu.java.lab4.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto {
    private Integer status;
    private String error;
    private String message;
    private String path;
    private LocalDateTime timestamp;
}
