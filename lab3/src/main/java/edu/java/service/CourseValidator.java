package edu.java.service;

import edu.java.exception.GradingFormulaException;
import edu.java.exception.ValidationException;
import edu.java.model.Course;
import edu.java.model.Exam;
import edu.java.model.GradingConstants;
import edu.java.model.LabWork;

public final class CourseValidator {
    private CourseValidator() {}

    public static void validateCourseFormula(int maxTotalLabPoints, int maxTotalExamPoints) {
        if (maxTotalLabPoints < 0 || maxTotalExamPoints < 0) {
            throw new ValidationException("Max points for labs and exams must be non-negative.");
        }

        int total = maxTotalLabPoints + maxTotalExamPoints;
        if (total > GradingConstants.MAX_TOTAL_POINTS) {
            throw new GradingFormulaException(
                    String.format("Course formula total (%d) exceeds max allowed points (%d)",
                            total, GradingConstants.MAX_TOTAL_POINTS)
            );
        }
    }

    public static void validateCourseAfterAddingLab(Course course, LabWork newLab) {
        int totalLabPoints = course.getLabWorks().stream()
                .mapToInt(LabWork::getMaxPoints)
                .sum() + newLab.getMaxPoints();

        int maxAllowedLabPoints = course.getMaxTotalLabPoints();

        if (totalLabPoints > maxAllowedLabPoints) {
            throw new GradingFormulaException(
                    String.format("Adding this lab would exceed maximum allowed lab points for this course. Current: %d, Max: %d",
                            totalLabPoints, maxAllowedLabPoints)
            );
        }
    }

    public static void validateCourseAfterAddingExam(Course course, Exam newExam) {
        int totalExamPoints = course.getExams().stream()
                .mapToInt(Exam::getMaxPoints)
                .sum() + newExam.getMaxPoints();

        int maxAllowedExamPoints = course.getMaxTotalExamPoints();

        if (totalExamPoints > maxAllowedExamPoints) {
            throw new GradingFormulaException(
                    String.format("Adding this exam would exceed maximum allowed exam points for this course. Current: %d, Max: %d",
                            totalExamPoints, maxAllowedExamPoints)
            );
        }
    }
}