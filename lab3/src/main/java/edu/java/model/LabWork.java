package edu.java.model;

import java.time.LocalDate;
import java.util.Objects;

public class LabWork {

    private final String labId;
    private final String title;
    private final int maxPoints;
    private final LocalDate deadline;
    private final int penaltyPerDay;

    public LabWork(String labId, String title, int maxPoints, LocalDate deadline, int penaltyPerDay) {
        this.labId = labId;
        this.title = title;
        this.maxPoints = maxPoints;
        this.deadline = deadline;
        this.penaltyPerDay = penaltyPerDay;
    }

    public LabWork(String labId, String title, int maxPoints, LocalDate deadline) {
        this(labId, title, maxPoints, deadline, GradingConstants.DEFAULT_PENALTY_PER_DAY);
    }

    public String getLabId() {
        return labId;
    }

    public String getTitle() {
        return title;
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public int getPenaltyPerDay() {
        return penaltyPerDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LabWork labWork = (LabWork) o;
        return Objects.equals(labId, labWork.labId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(labId);
    }
}