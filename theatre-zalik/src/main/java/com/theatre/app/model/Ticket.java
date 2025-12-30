package com.theatre.app.model;

import static com.theatre.app.validation.ModelValidator.*;

import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;


public record Ticket(
        @NonNull UUID id,
        @NonNull MovieSchedule schedule,
        @NonNull LocalDate date,
        @NonNull BigDecimal price
) {
    public Ticket {
        validatePositive(price, "Ticket price");
    }
}