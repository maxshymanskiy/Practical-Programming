package edu.java.lab4.dto.response;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentGradeDto {
    private Long studentId;
    private String studentName;
    private String studentNumber;
    private List<Double> labGrades; // Ordered by lab number
    private Double labTotal;
    private Double examGrade;
    private Double totalGrade;
    private Boolean passed;
}
