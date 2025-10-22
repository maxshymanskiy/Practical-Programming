package edu.java.model;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String id;
    private String name;
    private List<Student> students;
    private List<LabWork> labWorks;
    private List<Exam> exams;

    public Course(String id, String name) {
        this.id = id;
        this.name = name;
        this.students = new ArrayList<>();
        this.labWorks = new ArrayList<>();
        this.exams = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addLabWork(LabWork labWork) {
        labWorks.add(labWork);
    }

    public void addExam(Exam exam) {
        exams.add(exam);
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public List<Student> getStudents() { return new ArrayList<>(students); }
    public List<LabWork> getLabWorks() { return new ArrayList<>(labWorks); }
    public List<Exam> getExams() { return new ArrayList<>(exams); }
}