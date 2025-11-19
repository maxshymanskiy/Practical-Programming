package edu.java.lab4.dto.response;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JournalDto {
    private Long courseId;
    private String courseName;
    private String academicYear;
    private Integer labWeight;
    private Integer examWeight;
    private Integer maxGrade;
    private List<StudentGradeDto> studentGrades;
}
