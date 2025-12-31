package com.theatre.app.model;

import static com.theatre.app.util.validation.ModelValidator.*;

import java.time.LocalDate;
import java.time.LocalTime;

public record MovieSchedule(
        String id,
        Movie movie,
        Hall hall,
        LocalTime startTime,
        double price,
        LocalDate startDate,
        LocalDate endDate
) {
    public MovieSchedule {
        validateNotBlank(id, "Schedule ID");
        validateNotNull(movie, "Movie");
        validateNotNull(hall, "Hall");
        validateNotNull(startTime, "Start time");
        validatePositive(price, "Price");
        validateDateRange(startDate, endDate);
    }

    public boolean isActive(LocalDate date) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }
}