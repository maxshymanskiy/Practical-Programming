package edu.java.model;

import java.time.LocalDate;

public record LabWork(
        String title,
        LocalDate deadline,
        double maxScore,
        double penaltyPerDay) {
}
