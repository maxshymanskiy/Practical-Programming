package edu.java.model;

import edu.java.validation.SubmissionValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Student {

    private final String studentId;
    private final String name;
    private final String email;

    private final Map<LabWork, Integer> labSubmissions = new HashMap<>();
    private final Map<Exam, Integer> examGrades = new HashMap<>();

    public Student(String studentId, String name, String email) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
    }

    public void submitLab(LabWork labWork, int points) {
        SubmissionValidator.validateLabPoints(points, labWork);
        labSubmissions.put(labWork, points);
    }

    public void takeExam(Exam exam, int points) {
        SubmissionValidator.validateExamPoints(points, exam);
        examGrades.put(exam, points);
    }

    public int calculateLabPoints() {
        return labSubmissions.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int calculateExamPoints() {
        return examGrades.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int calculateTotalGrade() {
        return calculateLabPoints() + calculateExamPoints();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Map<LabWork, Integer> getLabSubmissions() {
        return new HashMap<>(labSubmissions);
    }

    public Map<Exam, Integer> getExamGrades() {
        return new HashMap<>(examGrades);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }
}