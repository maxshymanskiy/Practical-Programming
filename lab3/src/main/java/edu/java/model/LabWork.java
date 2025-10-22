package edu.java.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class LabWork {
    private String id;
    private String title;
    private LocalDate deadline;
    private double penaltyPerDay;
    private List<Task> tasks;

    public LabWork(String id, String title, LocalDate deadline) {
        this.id = id;
        this.title = title;
        this.deadline = deadline;
        this.penaltyPerDay = GradingConstants.DEFAULT_PENALTY_PER_DAY;
        this.tasks = new ArrayList<>();
    }

    public double calculateFinalScore(double pointsEarned, LocalDate submissionDate) {
        if (submissionDate.isAfter(deadline)) {
            long daysLate = ChronoUnit.DAYS.between(deadline, submissionDate);
            double penalty = daysLate * penaltyPerDay;
            return Math.max(0, pointsEarned - penalty);
        }
        return pointsEarned;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public LocalDate getDeadline() { return deadline; }
    public List<Task> getTasks() { return new ArrayList<>(tasks); }
}