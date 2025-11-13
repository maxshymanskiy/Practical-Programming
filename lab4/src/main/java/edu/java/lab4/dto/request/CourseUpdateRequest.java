package edu.java.lab4.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static edu.java.lab4.constant.GradingConstants.MAX_COURSE_NAME_LENGTH;
import static edu.java.lab4.constant.GradingConstants.MIN_COURSE_NAME_LENGTH;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseUpdateRequest {

    @Size(min = MIN_COURSE_NAME_LENGTH,
            max = MAX_COURSE_NAME_LENGTH)
    private String name;

    @Size(max = 500)
    private String description;

    @Pattern(regexp = "\\d{4}-\\d{4}", message = "Academic year must be in format YYYY-YYYY")
    private String academicYear;

    @Min(value = 1)
    @Max(value = 50)
    private Integer labWeight;

    @Min(value = 1)
    @Max(value = 20)
    private Integer labCount;

    @Min(value = 1)
    @Max(value = 100)
    private Integer examWeight;
}
