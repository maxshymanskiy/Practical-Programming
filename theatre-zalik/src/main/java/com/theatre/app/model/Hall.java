package com.theatre.app.model;

import com.theatre.app.validation.ModelValidator;
import lombok.NonNull;

// NOTE: Record is perfect for immutable hall configuration
public record Hall(
        @NonNull String id,
        @NonNull String name,
        @NonNull HallType type,
        int capacity
) {
    // Compact constructor delegates to centralized validation
    public Hall {
        ModelValidator.validateNotBlank(id, "Hall ID");
        ModelValidator.validateNotBlank(name, "Hall name");
        ModelValidator.validatePositive(capacity, "Hall capacity");
    }
}

