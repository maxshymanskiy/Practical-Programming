package edu.java.model;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private final String name;
    private final List<Exam> exams;
    private final List<LabWork> labWorks;
    private final List<Student> students;
    private GradingFormula formula;

    public Course(String name) {
        this.name = name;
        this.exams = new ArrayList<>();
        this.labWorks = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Exam> getExams() {
        return new ArrayList<>(exams);
    }

    public List<LabWork> getLabWorks() {
        return new ArrayList<>(labWorks);
    }

    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }

    public GradingFormula getFormula() {
        return formula;
    }

    public void setFormula(GradingFormula formula) {
        this.formula = formula;
    }

    public void addExam(Exam exam) {
        exams.add(exam);
    }

    public void addLabWork(LabWork labWork) {
        labWorks.add(labWork);
    }

    public void addStudent(Student student) {
        students.add(student);
    }
}
