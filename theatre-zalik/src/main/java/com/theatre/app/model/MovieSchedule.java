package com.theatre.app.model;

import com.theatre.app.validation.ModelValidator;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

// NOTE: Record is perfect for immutable schedule data (value object pattern)
public record MovieSchedule(
        @NonNull String id,
        @NonNull Movie movie,
        @NonNull Hall hall,
        @NonNull LocalTime startTime,
        @NonNull BigDecimal price,
        @NonNull LocalDate startDate,
        @NonNull LocalDate endDate
) {
    // Compact constructor delegates to centralized validation
    public MovieSchedule {
        ModelValidator.validateNotBlank(id, "Schedule ID");
        ModelValidator.validatePositive(price, "Price");
        ModelValidator.validateDateRange(startDate, endDate);
    }

    public boolean isActive(LocalDate date) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }
}

