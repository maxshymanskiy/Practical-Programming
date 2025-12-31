package com.theatre.app.model;

import static com.theatre.app.util.validation.ModelValidator.*;

public record Hall(
        String id,
        String name,
        HallType type,
        int capacity
) {
    public Hall {
        validateNotBlank(id, "Hall ID");
        validateNotBlank(name, "Hall name");
        validateNotNull(type, "Hall type");
        validatePositive(capacity, "Hall capacity");
    }
}