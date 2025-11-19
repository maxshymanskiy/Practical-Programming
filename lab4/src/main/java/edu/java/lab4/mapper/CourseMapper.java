package edu.java.lab4.mapper;

import edu.java.lab4.dto.request.CourseCreateDto;
import edu.java.lab4.dto.response.CourseDetailDto;
import edu.java.lab4.dto.response.CourseDto;
import edu.java.lab4.dto.response.StudentDto;
import edu.java.lab4.entity.Course;
import edu.java.lab4.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CourseMapper {

    private final LabWorkMapper labWorkMapper;
    private final ExamMapper examMapper;

    /**
     * CreateRequest → Entity
     */
    public Course toEntity(CourseCreateDto request) {
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
    public CourseDto toResponse(Course course) {
        return CourseDto.builder()
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
    public CourseDetailDto toDetailResponse(Course course) {
        return CourseDetailDto.builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .academicYear(course.getAcademicYear())
                .labWeight(course.getLabWeight())
                .labCount(course.getLabCount())
                .examWeight(course.getExamWeight())
                .maxGrade(course.calculateMaxGrade())
                .students(course.getStudents() != null ? course.getStudents()
                        .stream()
                        .map(this::toStudentResponse)
                        .toList() : List.of())
                .labWorks(course.getLabWorks() != null ? course.getLabWorks()
                        .stream()
                        .map(labWorkMapper::toResponse)
                        .toList() : List.of())
                .exams(course.getExams() != null ? course.getExams()
                        .stream()
                        .map(examMapper::toResponse)
                        .toList() : List.of())

                .createdAt(course.getCreatedAt())
                .build();
    }

    /*
     For fixing circular dependency reference in CourseMapper
     ┌─────┐
     |  courseMapper defined in file CourseMapper.class
     ↑     ↓
     |  studentMapper defined in file StudentMapper.class
     └─────┘
    */
    private StudentDto toStudentResponse(Student student) {
        return StudentDto.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .fullName(student.getFullName())
                .email(student.getEmail())
                .studentNumber(student.getStudentNumber())
                .enrolledCoursesCount(student.getCourses().size())
                .createdAt(student.getCreatedAt())
                .build();
    }
}