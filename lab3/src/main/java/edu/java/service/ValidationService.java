package edu.java.service;

import edu.java.exception.ValidationException;
import edu.java.model.Course;
import edu.java.model.Exam;
import edu.java.model.GradingConstants;
import edu.java.model.LabWork;
import edu.java.model.Student;

public class ValidationService {
    private ValidationService() {}

    public static void validateCourseFormula(int maxTotalLabPoints, int maxTotalExamPoints) {
        if (maxTotalLabPoints < 0 || maxTotalExamPoints < 0) {
            throw new ValidationException("Max points for labs and exams must be non-negative.");
        }

        int total = maxTotalLabPoints + maxTotalExamPoints;
        if (total > GradingConstants.MAX_TOTAL_POINTS) {
            throw new ValidationException(
                    String.format("Course formula total (%d) exceeds max allowed points (%d)",
                            total, GradingConstants.MAX_TOTAL_POINTS)
            );
        }
    }

    public static void validateLabPoints(int points, LabWork labWork) {
        if (points < 0 || points > labWork.getMaxPoints()) {
            throw new ValidationException(
                    String.format("Lab points must be between 0 and %d, but got %d",
                            labWork.getMaxPoints(), points)
            );
        }
    }

    public static void validateExamPoints(int points, Exam exam) {
        if (points < 0 || points > exam.getMaxPoints()) {
            throw new ValidationException(
                    String.format("Exam points must be between 0 and %d, but got %d",
                            exam.getMaxPoints(), points)
            );
        }
    }

    public static void validateCourseAfterAddingLab(Course course, LabWork newLab) {
        int totalLabPoints = course.getLabWorks().stream()
                .mapToInt(LabWork::getMaxPoints)
                .sum() + newLab.getMaxPoints();

        int maxAllowedLabPoints = course.getMaxTotalLabPoints();

        if (totalLabPoints > maxAllowedLabPoints) {
            throw new ValidationException(
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
            throw new ValidationException(
                    String.format("Adding this exam would exceed maximum allowed exam points for this course. Current: %d, Max: %d",
                            totalExamPoints, maxAllowedExamPoints)
            );
        }
    }

    public static void validateStudentTotalGrade(Student student, Course course) {
        int totalPoints = student.calculateTotalGrade();
        int maxPoints = course.getMaxPoints();

        if (totalPoints > maxPoints) {
            throw new ValidationException(
                    String.format("Student %s has %d points in course %s, which exceeds maximum %d",
                            student.getName(), totalPoints, course.getCourseName(), maxPoints)
            );
        }
    }
}