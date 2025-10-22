package edu.java.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Student {
    private String id;
    private String name;
    private String email;
    private Map<String, Double> labGrades; // labId -> points
    private Map<String, Double> examGrades; // examId -> points
    private Map<String, LocalDate> submissionDates;

    public Student(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.labGrades = new HashMap<>();
        this.examGrades = new HashMap<>();
        this.submissionDates = new HashMap<>();
    }

    public void addLabGrade(String labId, double points, LocalDate submissionDate) {
        labGrades.put(labId, points);
        submissionDates.put(labId, submissionDate);
    }

    public void addExamGrade(String examId, double points) {
        examGrades.put(examId, points);
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public Double getLabGrade(String labId) { return labGrades.get(labId); }
    public Double getExamGrade(String examId) { return examGrades.get(examId); }
    public LocalDate getSubmissionDate(String assignmentId) { return submissionDates.get(assignmentId); }
}
