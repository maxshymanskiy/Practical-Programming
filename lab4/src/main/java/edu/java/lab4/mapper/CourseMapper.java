package edu.java.lab4.mapper;

import edu.java.lab4.dto.request.CourseCreateRequest;
import edu.java.lab4.dto.response.CourseDetailResponse;
import edu.java.lab4.dto.response.CourseResponse;
import edu.java.lab4.entity.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseMapper {

    private final StudentMapper studentMapper;
    private final LabWorkMapper labWorkMapper;
    private final ExamMapper examMapper;

    /**
     * CreateRequest → Entity
     */
    public Course toEntity(CourseCreateRequest request) {
        return Course.builder()
                .name(request.getName())
                .description(request.getDescription())
                .academicYear(request.getAcademicYear())
                .labWeight(request.getLabWeight())
                .labCount(request.getLabCount())
                .examWeight(request.getExamWeight())
                .build();
    }

    /**
     * Entity → Response
     */
    public CourseResponse toResponse(Course course) {
        return CourseResponse.builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .academicYear(course.getAcademicYear())
                .labWeight(course.getLabWeight())
                .labCount(course.getLabCount())
                .examWeight(course.getExamWeight())
                .maxGrade(course.calculateMaxGrade())
                .enrolledStudents(course.getStudents().size())
                .createdAt(course.getCreatedAt())
                .updatedAt(course.getUpdatedAt())
                .build();
    }

    /**
     * Entity → DetailResponse
     */
    public CourseDetailResponse toDetailResponse(Course course) {
        return CourseDetailResponse.builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .academicYear(course.getAcademicYear())
                .labWeight(course.getLabWeight())
                .labCount(course.getLabCount())
                .examWeight(course.getExamWeight())
                .maxGrade(course.calculateMaxGrade())
                .students(course.getStudents()
                        .stream()
                        .map(studentMapper::toResponse)
                        .toList())
                .labWorks(course.getLabWorks()
                        .stream()
                        .map(labWorkMapper::toResponse)
                        .toList())
                .exams(course.getExams()
                        .stream()
                        .map(examMapper::toResponse)
                        .toList())
                .createdAt(course.getCreatedAt())
                .build();
    }
}