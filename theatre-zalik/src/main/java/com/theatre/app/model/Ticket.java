package com.theatre.app.model;

import static com.theatre.app.validation.ModelValidator.*;

import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;


public record Ticket(
        @NonNull String id,
        @NonNull MovieSchedule schedule,
        @NonNull LocalDate date,
        @NonNull BigDecimal price
) {
    public Ticket {
        validateNotBlank(id, "Ticket ID");
        validatePositive(price, "Ticket price");
    }
}