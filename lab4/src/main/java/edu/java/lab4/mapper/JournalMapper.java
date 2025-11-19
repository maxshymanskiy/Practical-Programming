package edu.java.lab4.mapper;

import edu.java.lab4.constant.GradingConstants;
import edu.java.lab4.dto.response.JournalDto;
import edu.java.lab4.dto.response.StudentGradeDto;
import edu.java.lab4.entity.Course;
import edu.java.lab4.entity.Student;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JournalMapper {

    public JournalDto toJournalResponse(Course course, List<StudentGradeDto> studentGrades) {
        return JournalDto.builder()
                .courseId(course.getId())
                .courseName(course.getName())
                .academicYear(course.getAcademicYear())
                .labWeight(course.getLabWeight())
                .examWeight(course.getExamWeight())
                .maxGrade(course.calculateMaxGrade())
                .studentGrades(studentGrades)
                .build();
    }

    public StudentGradeDto toStudentGradeResponse(
            Student student,
            List<Double> labGrades,
            Double labTotal,
            Double examGrade,
            Integer maxGrade
    ) {
        Double totalGrade = labTotal + (examGrade != null ? examGrade : 0.0);
        Boolean passed = totalGrade >= GradingConstants.PASSING_GRADE;

        return StudentGradeDto.builder()
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