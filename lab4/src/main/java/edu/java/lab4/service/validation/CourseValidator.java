package edu.java.lab4.service.validation;

import edu.java.lab4.exception.DuplicateEntityException;
import edu.java.lab4.exception.InvalidGradeFormulaException;
import edu.java.lab4.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static edu.java.lab4.constant.GradingConstants.MAX_GRADE;

@Component
@RequiredArgsConstructor
public class CourseValidator {

    private final CourseRepository courseRepository;

    public void validateCourseUniqueness(String name, String academicYear) {
        if (courseRepository.existsByNameAndAcademicYear(name, academicYear)) {
            throw new DuplicateEntityException("Course", "name + year", name + " " + academicYear);
        }
    }

    public void validateCourseNameOnUpdate(String newName, String oldName, String academicYear) {
        if (!newName.equals(oldName) && courseRepository.existsByNameAndAcademicYear(newName, academicYear)) {
            throw new DuplicateEntityException("Course", "name", newName);
        }
    }

    public void validateGradingFormula(Integer labWeight, Integer labCount, Integer examWeight) {
        if (labWeight <= 0 || labCount <= 0 || examWeight <= 0) {
            throw InvalidGradeFormulaException.invalidWeights();
        }

        int maxGrade = (labWeight * labCount) + examWeight;
        if (maxGrade > MAX_GRADE) {
            throw InvalidGradeFormulaException.invalidTotal(maxGrade, MAX_GRADE);
        }
    }
}