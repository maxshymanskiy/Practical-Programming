package edu.java.service;

import edu.java.exception.GradeOutOfBoundsException;
import edu.java.exception.ValidationException;
import edu.java.model.Course;
import edu.java.model.Exam;
import edu.java.model.LabWork;
import edu.java.model.Student;

public final class SubmissionValidator {
    private SubmissionValidator() {}

    public static void validateLabPoints(int points, LabWork labWork) {
        if (points < 0 || points > labWork.maxPoints()) {
            throw new GradeOutOfBoundsException(
                    String.format("Lab points must be between 0 and %d, but got %d",
                            labWork.maxPoints(), points)
            );
        }
    }

    public static void validateExamPoints(int points, Exam exam) {
        if (points < 0 || points > exam.getMaxPoints()) {
            throw new GradeOutOfBoundsException(
                    String.format("Exam points must be between 0 and %d, but got %d",
                            exam.getMaxPoints(), points)
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