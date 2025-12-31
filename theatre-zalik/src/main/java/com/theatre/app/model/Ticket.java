package com.theatre.app.model;

import static com.theatre.app.util.validation.ModelValidator.*;

import java.time.LocalDate;


public record Ticket(
        String id,
        MovieSchedule schedule,
        LocalDate date,
        double price
) {
    public Ticket {
        validateNotBlank(id, "Ticket ID");
        validateNotNull(schedule, "Movie schedule");
        validateNotNull(date, "Date");
        validatePositive(price, "Ticket price");
    }
}