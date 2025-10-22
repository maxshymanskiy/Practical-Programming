package edu.java.model;

public class Task {
    private String description;
    private double points;

    public Task(String description, double points) {
        this.description = description;
        this.points = points;
    }

    public String getDescription() { return description; }
    public double getPoints() { return points; }
}