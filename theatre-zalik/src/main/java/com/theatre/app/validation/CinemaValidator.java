package com.theatre.app.validation;

import com.theatre.app.exception.TicketOperationException;
import com.theatre.app.model.MovieSchedule;
import com.theatre.app.model.Ticket;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Validator for cinema-related business rules.
 * Separates validation logic from service implementation (Single Responsibility Principle).
 */
public final class CinemaValidator {

    private CinemaValidator() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Finds schedule or throws exception if not found.
     */
    public static MovieSchedule requireSchedule(Map<String, MovieSchedule> schedules, String scheduleId) {
        return Optional.ofNullable(schedules.get(scheduleId))
                .orElseThrow(() -> new TicketOperationException("Schedule not found: " + scheduleId));
    }

    /**
     * Validates that schedule is active for the given date.
     */
    public static void validateScheduleActive(MovieSchedule schedule, LocalDate date) {
        if (!schedule.isActive(date)) {
            throw new TicketOperationException("Movie is not showing on this date: " + date);
        }
    }

    /**
     * Validates that hall has available capacity.
     */
    public static void validateHallCapacity(MovieSchedule schedule, LocalDate date,
                                            Map<UUID, Ticket> soldTickets) {
        long ticketsSoldForSession = soldTickets.values().stream()
                .filter(t -> t.schedule().id().equals(schedule.id()))
                .filter(t -> t.date().equals(date))
                .count();

        if (ticketsSoldForSession >= schedule.hall().capacity()) {
            throw new TicketOperationException("Hall is full for this session.");
        }
    }

    /**
     * Validates ticket exists before return operation.
     */
    public static Ticket requireTicket(Map<UUID, Ticket> soldTickets, UUID ticketId) {
        return Optional.ofNullable(soldTickets.get(ticketId))
                .orElseThrow(() -> new TicketOperationException("Ticket not found: " + ticketId));
    }
}


