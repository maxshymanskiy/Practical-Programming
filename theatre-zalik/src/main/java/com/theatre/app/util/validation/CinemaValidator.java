package com.theatre.app.util.validation;

import com.theatre.app.exception.TicketOperationException;
import com.theatre.app.model.MovieSchedule;
import com.theatre.app.model.Ticket;

import java.time.LocalDate;
import java.util.Map;

import static com.theatre.app.util.validation.ModelValidator.validateNotBlank;
import static com.theatre.app.util.validation.ModelValidator.validateNotNull;

public final class CinemaValidator {
    private CinemaValidator() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static MovieSchedule validateScheduleExists(Map<String, MovieSchedule> schedules, String scheduleId) {
        validateNotBlank(scheduleId, "Schedule ID");
        MovieSchedule schedule = schedules.get(scheduleId);
        if (schedule == null) {
            throw new TicketOperationException("Schedule not found: " + scheduleId);
        }
        return schedule;
    }

    public static void validateScheduleActive(MovieSchedule schedule, LocalDate date) {
        validateNotNull(date, "Date");
        if (!schedule.isActive(date)) {
            throw new TicketOperationException("Movie is not showing on this date: " + date);
        }
    }

    public static void validateHallCapacity(MovieSchedule schedule, LocalDate date, Map<String, Ticket> soldTickets) {
        long ticketsSoldForSession = soldTickets.values()
                .stream()
                .filter(t ->
                        t.schedule().id().equals(schedule.id())
                        && t.date().equals(date)
                )
                .count();

        if (ticketsSoldForSession >= schedule.hall().capacity()) {
            throw new TicketOperationException("Hall is full for this session.");
        }
    }

    public static Ticket validateTicketExists(Map<String, Ticket> soldTickets, String ticketId) {
        validateNotBlank(ticketId, "Ticket ID");
        Ticket ticket = soldTickets.get(ticketId);
        if (ticket == null) {
            throw new TicketOperationException("Ticket not found: " + ticketId);
        }
        return ticket;
    }
}