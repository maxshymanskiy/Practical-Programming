package com.theatre.app.validation;

import lombok.NonNull;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;

public final class ModelValidator {
    private ModelValidator() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static void validateNotBlank(@NonNull String value, String fieldName) {
        if (value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " cannot be blank");
        }
    }

    public static void validatePositive(int value, String fieldName) {
        if (value <= 0) {
            throw new IllegalArgumentException(fieldName + " must be positive");
        }
    }

    public static void validatePositive(@NonNull BigDecimal value, String fieldName) {
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(fieldName + " must be positive");
        }
    }

    public static void validatePositive(@NonNull Duration value, String fieldName) {
        if (value.isNegative() || value.isZero()) {
            throw new IllegalArgumentException(fieldName + " must be positive");
        }
    }

    public static void validateDateRange(@NonNull LocalDate startDate, @NonNull LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }
    }
}

