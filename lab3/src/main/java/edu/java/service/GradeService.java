package edu.java.service;


import edu.java.model.Course;
import edu.java.model.GradingConstants;
import edu.java.model.Student;

import java.time.LocalDate;

public class GradeService {

    public double calculateFinalGrade(Student student, Course course) {
        double labTotal = calculateLabTotal(student, course);
        double examTotal = calculateExamTotal(student, course);
        return labTotal + examTotal;
    }

    private double calculateLabTotal(Student student, Course course) {
        return course.getLabWorks().stream()
                .mapToDouble(lab -> {
                    Double points = student.getLabGrade(lab.getId());
                    if (points != null) {
                        LocalDate submissionDate = student.getSubmissionDate(lab.getId());
                        return lab.calculateFinalScore(points, submissionDate);
                    }
                    return 0.0;
                })
                .sum();
    }

    private double calculateExamTotal(Student student, Course course) {
        return course.getExams().stream()
                .mapToDouble(exam -> {
                    Double points = student.getExamGrade(exam.getId());
                    return points != null ? points : 0.0;
                })
                .sum();
    }

    public String getGradeLetter(double finalScore) {
        if (finalScore >= GradingConstants.EXCELLENT_MIN) {
            return "Excellent";
        } else if (finalScore >= GradingConstants.GOOD_MIN) {
            return "Good";
        } else if (finalScore >= GradingConstants.SATISFACTORY_MIN) {
            return "Satisfactory";
        } else {
            return "Fail";
        }
    }

    public String generateCourseJournal(Course course) {
        StringBuilder journal = new StringBuilder();
        journal.append("Course Journal: ").append(course.getName()).append("\n");
        journal.append("=".repeat(50)).append("\n\n");

        course.getStudents().stream()
                .sorted((s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()))
                .forEach(student -> {
                    double finalGrade = calculateFinalGrade(student, course);
                    String gradeLetter = getGradeLetter(finalGrade);

                    journal.append(String.format("Student: %s (ID: %s)\n", student.getName(), student.getId()));
                    journal.append("Lab Works:\n");

                    course.getLabWorks().forEach(lab -> {
                        Double grade = student.getLabGrade(lab.getId());
                        String gradeStr = (grade != null) ? String.format("%.1f", grade) : "Not submitted";
                        journal.append(String.format("  - %s: %s/10\n", lab.getTitle(), gradeStr));
                    });

                    journal.append("Exams:\n");
                    course.getExams().forEach(exam -> {
                        Double grade = student.getExamGrade(exam.getId());
                        String gradeStr = (grade != null) ? String.format("%.1f", grade) : "Not submitted";
                        journal.append(String.format("  - %s: %s/60\n", exam.getTitle(), gradeStr));
                    });

                    journal.append(String.format("Final Grade: %.2f/100 (%s)\n\n", finalGrade, gradeLetter));
                });

        return journal.toString();
    }
}