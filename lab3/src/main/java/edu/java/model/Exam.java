package edu.java.model;

import java.util.List;

public record Exam(
        String title,
        List<String> tasks,
        double maxScore) {

    public Exam(String title, List<String> tasks, double maxScore) {
        this.title = title;
        this.tasks = List.copyOf(tasks);
        this.maxScore = maxScore;
    }
}
