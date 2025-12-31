package com.theatre.app.model;

import static com.theatre.app.util.validation.ModelValidator.*;

import lombok.NonNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record MovieSchedule(
        @NonNull String id,
        @NonNull Movie movie,
        @NonNull Hall hall,
        @NonNull LocalTime startTime,
        double price,
        @NonNull LocalDate startDate,
        @NonNull LocalDate endDate
) {
    public MovieSchedule {
        validateNotBlank(id, "Schedule ID");
        validatePositive(price, "Price");
        validateDateRange(startDate, endDate);
    }

    public boolean isActive(LocalDate date) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }
}