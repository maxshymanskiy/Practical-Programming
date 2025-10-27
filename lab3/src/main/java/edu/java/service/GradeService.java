package edu.java.service;

import edu.java.model.Course;
import edu.java.model.GradingConstants;
import edu.java.model.LabWork;
import edu.java.model.Student;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;

public class GradeService {

    public int calculateLabWithPenalty(Student student, LabWork lab, LocalDate submissionDate) {
        Integer points = student.getLabSubmissions().get(lab);
        if (points == null) {
            return 0;
        }

        if (submissionDate.isAfter(lab.deadline())) {
            long daysLate = ChronoUnit.DAYS.between(lab.deadline(), submissionDate);
            int penalty = (int) (daysLate * lab.penaltyPerDay());
            return Math.max(0, points - penalty);
        }

        return points;
    }

    public Map<Student, Integer> generateCourseJournal(Course course) {
        return course.getStudents()
                .stream()
                .collect(Collectors.toMap(student -> student, Student::calculateTotalGrade));
    }

    public String getGradeLetter(int totalPoints) {
        if (totalPoints >= GradingConstants.EXCELLENT_MIN) {
            return "Excellent";
        } else if (totalPoints >= GradingConstants.GOOD_MIN) {
            return "Good";
        } else if (totalPoints >= GradingConstants.SATISFACTORY_MIN) {
            return "Satisfactory";
        } else {
            return "Fail";
        }
    }

    public void validateStudentGrade(Student student, Course course) {
        SubmissionValidator.validateStudentTotalGrade(student, course);
    }
}