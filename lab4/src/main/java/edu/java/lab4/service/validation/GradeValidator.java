package edu.java.lab4.service.validation;

import edu.java.lab4.entity.Course;
import edu.java.lab4.entity.Exam;
import edu.java.lab4.entity.LabWork;
import edu.java.lab4.exception.InvalidGradeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GradeValidator {

    public void validateLabGrade(LabWork labWork, Double grade) {
        Course course = labWork.getCourse();
        if (grade == null) {
            return;
        }

        if (grade < 0) {
            throw new InvalidGradeException("Grade cannot be negative");
        }

        Integer maxLabGrade = course.getLabWeight();
        if (grade > maxLabGrade) {
            throw new InvalidGradeException(
                    String.format("Grade %.2f exceeds maximum allowed lab grade (%d)", grade, maxLabGrade)
            );
        }
    }


    public void validateExamGrade(Exam exam, Double grade) {
        Course course = exam.getCourse();
        if (grade == null) {
            return;
        }

        if (grade < 0) {
            throw new InvalidGradeException("Grade cannot be negative");
        }

        Integer maxExamGrade = course.getExamWeight();
        if (grade > maxExamGrade) {
            throw new InvalidGradeException(
                    String.format("Grade %.2f exceeds maximum allowed exam grade (%d)", grade, maxExamGrade)
            );
        }
    }
}
