package com.theatre.app.model;

import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

// NOTE: Record is perfect for immutable ticket data (once created, ticket shouldn't change)
public record Ticket(
        @NonNull UUID id,
        @NonNull MovieSchedule schedule,
        @NonNull LocalDate date,
        @NonNull BigDecimal price
) {
}

