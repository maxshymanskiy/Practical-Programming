package edu.java.lab4.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDetailResponse {
    private Long id;
    private String name;
    private String description;
    private String academicYear;
    private Integer labWeight;
    private Integer labCount;
    private Integer examWeight;
    private Integer maxGrade;
    private List<StudentResponse> students;
    private List<LabWorkResponse> labWorks;
    private List<ExamResponse> exams;
    private LocalDateTime createdAt;
}
