package edu.java.model;

import java.time.LocalDate;
import java.util.Objects;

public record LabWork(
        String labId,
        String title,
        int maxPoints,
        LocalDate deadline,
        int penaltyPerDay) {

    public LabWork(String labId, String title, int maxPoints, LocalDate deadline) {
        this(labId, title, maxPoints, deadline, GradingConstants.DEFAULT_PENALTY_PER_DAY);
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