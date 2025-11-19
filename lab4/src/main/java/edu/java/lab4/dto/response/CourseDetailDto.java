package edu.java.lab4.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDetailDto {
    private Long id;
    private String name;
    private String description;
    private String academicYear;
    private Integer labWeight;
    private Integer labCount;
    private Integer examWeight;
    private Integer maxGrade;
    private List<StudentDto> students;
    private List<LabWorkDto> labWorks;
    private List<ExamDto> exams;
    private LocalDateTime createdAt;
}
