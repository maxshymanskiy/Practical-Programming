package edu.java.lab4.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LabWorkDto {
    @NotBlank(message = "Lab ID cannot be blank")
    private String labId;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @Positive(message = "Max points must be positive")
    private int maxPoints;

    @FutureOrPresent(message = "Deadline must be in the present or future")
    private LocalDate deadline;

    @Positive(message = "Penalty must be positive")
    private int penaltyPerDay;
}
