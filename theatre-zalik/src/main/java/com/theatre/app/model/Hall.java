package com.theatre.app.model;

import static com.theatre.app.validation.ModelValidator.*;

import lombok.NonNull;

public record Hall(
        @NonNull String id,
        @NonNull String name,
        @NonNull HallType type,
        int capacity
) {
    public Hall {
        validateNotBlank(id, "Hall ID");
        validateNotBlank(name, "Hall name");
        validatePositive(capacity, "Hall capacity");
    }
}