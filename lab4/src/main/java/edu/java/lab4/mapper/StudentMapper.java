package edu.java.lab4.mapper;

import edu.java.lab4.dto.request.StudentCreateRequest;
import edu.java.lab4.dto.response.CourseResponse;
import edu.java.lab4.dto.response.StudentDetailResponse;
import edu.java.lab4.dto.response.StudentResponse;
import edu.java.lab4.entity.Course;
import edu.java.lab4.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentMapper {

    public Student toEntity(StudentCreateRequest request) {
        return Student.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .studentNumber(request.getStudentNumber())
                .build();
    }

    public StudentResponse toResponse(Student student) {
        return StudentResponse.builder()
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

    public StudentDetailResponse toDetailResponse(Student student) {
        return StudentDetailResponse.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .studentNumber(student.getStudentNumber())
                .courses(student.getCourses()
                        .stream()
                        .map(this::toCourseResponse)
                        .toList())
                .createdAt(student.getCreatedAt())
                .build();
    }

    /*
     For fixing circular dependency reference in StudentMapper
     ┌─────┐
     |  courseMapper defined in file CourseMapper.class
     ↑     ↓
     |  studentMapper defined in file StudentMapper.class
     └─────┘
    */
    private CourseResponse toCourseResponse(Course course) {
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
}