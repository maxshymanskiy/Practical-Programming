package edu.java.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Exam {

    private final String examId;
    private final String title;
    private final int maxPoints;

    private final List<Task> tasks = new ArrayList<>();

    public Exam(String examId, String title, int maxPoints) {
        this.examId = examId;
        this.title = title;
        this.maxPoints = maxPoints;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public String getExamId() {
        return examId;
    }

    public String getTitle() {
        return title;
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exam exam = (Exam) o;
        return Objects.equals(examId, exam.examId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(examId);
    }
}