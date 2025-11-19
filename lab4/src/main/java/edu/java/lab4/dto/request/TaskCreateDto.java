package edu.java.lab4.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import static edu.java.lab4.constant.GradingConstants.MIN_TASK_DESCRIPTION_LENGTH;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskCreateDto {

    @NotNull(message = "Exam ID is required")
    private Long examId;

    @NotNull(message = "Variant number is required")
    @Min(value = 1)
    private Integer variantNumber;

    @NotBlank(message = "Title is required")
    @Size(max = 200)
    private String title;

    @NotBlank(message = "Description is required")
    @Size(min = MIN_TASK_DESCRIPTION_LENGTH)
    private String description;
}
