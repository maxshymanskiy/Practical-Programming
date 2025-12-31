package com.theatre.app.util.validation;

import java.time.Duration;
import java.time.LocalDate;

public final class ModelValidator {
    private ModelValidator() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static void validateNotNull(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " cannot be null");
        }
    }

    public static void validateNotBlank(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or blank");
        }
    }

    public static void validatePositive(int value, String fieldName) {
        if (value <= 0) {
            throw new IllegalArgumentException(fieldName + " must be positive, got: " + value);
        }
    }

    public static void validatePositive(double value, String fieldName) {
        if (value <= 0) {
            throw new IllegalArgumentException(fieldName + " must be positive, got: " + value);
        }
    }

    public static void validatePositive(Duration value, String fieldName) {
        validateNotNull(value, fieldName);
        if (value.isNegative() || value.isZero()) {
            throw new IllegalArgumentException(fieldName + " must be positive, got: " + value);
        }
    }

    public static void validateDateRange(LocalDate startDate, LocalDate endDate) {
        validateNotNull(startDate, "Start date");
        validateNotNull(endDate, "End date");
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date. Start: " + startDate + ", End: " + endDate);
        }
    }
}

