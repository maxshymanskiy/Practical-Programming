package edu.java.model;

import java.util.HashMap;
import java.util.Map;

public class Student {

    private final String id;
    private final String name;
    private final Map<String, Double> scores;

    public Student(String id, String name, Map<String, Double> scores) {
        this.id = id;
        this.name = name;
        this.scores = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<String, Double> getScores() {
        return new HashMap<>(scores);
    }

    public void addScore(String assignmentId, Double score) {
        this.scores.put(assignmentId, score);
    }
}
