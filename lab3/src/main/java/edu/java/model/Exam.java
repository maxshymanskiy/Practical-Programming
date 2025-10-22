package edu.java.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Exam {
    private String id;
    private String title;
    private LocalDate examDate;
    private List<Task> tasks;
    private List<String> variants;

    public Exam(String id, String title, LocalDate examDate) {
        this.id = id;
        this.title = title;
        this.examDate = examDate;
        this.tasks = new ArrayList<>();
        this.variants = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void addVariant(String variant) {
        variants.add(variant);
    }

    // Getters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public LocalDate getExamDate() { return examDate; }
    public List<Task> getTasks() { return new ArrayList<>(tasks); }
    public List<String> getVariants() { return new ArrayList<>(variants); }
}