package com.theatre.app.model;

import static com.theatre.app.util.validation.ModelValidator.*;

import lombok.NonNull;

import java.time.LocalDate;


public record Ticket(
        @NonNull String id,
        @NonNull MovieSchedule schedule,
        @NonNull LocalDate date,
        double price
) {
    public Ticket {
        validateNotBlank(id, "Ticket ID");
        validatePositive(price, "Ticket price");
    }
}