package edu.java.lab4.mapper;

import edu.java.lab4.dto.response.JournalResponse;
import edu.java.lab4.dto.response.StudentGradeResponse;
import edu.java.lab4.entity.Course;
import edu.java.lab4.entity.Student;
import org.springframework.stereotype.Component;

import java.util.List;

import static edu.java.lab4.constant.GradingConstants.PASSING_GRADE;

@Component
public class JournalMapper {

    public JournalResponse toJournalResponse(Course course, List<StudentGradeResponse> studentGrades) {
        return JournalResponse.builder()
                .courseId(course.getId())
                .courseName(course.getName())
                .academicYear(course.getAcademicYear())
                .labWeight(course.getLabWeight())
                .examWeight(course.getExamWeight())
                .maxGrade(course.calculateMaxGrade())
                .studentGrades(studentGrades)
                .build();
    }

    public StudentGradeResponse toStudentGradeResponse(
            Student student,
            List<Double> labGrades,
            Double labTotal,
            Double examGrade,
            Integer maxGrade
    ) {
        Double totalGrade = labTotal + (examGrade != null ? examGrade : 0.0);
        Boolean passed = totalGrade >= PASSING_GRADE;

        return StudentGradeResponse.builder()
                .studentId(student.getId())
                .studentName(student.getFullName())
                .studentNumber(student.getStudentNumber())
                .labGrades(labGrades)
                .labTotal(labTotal)
                .examGrade(examGrade)
                .totalGrade(totalGrade)
                .passed(passed)
                .build();
    }
}