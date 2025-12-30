package com.theatre.app.validation;

import com.theatre.app.exception.TicketOperationException;
import com.theatre.app.model.MovieSchedule;
import com.theatre.app.model.Ticket;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

public final class CinemaValidator {
    private CinemaValidator() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static MovieSchedule requireSchedule(Map<String, MovieSchedule> schedules, String scheduleId) {
        MovieSchedule schedule = schedules.get(scheduleId);
        if (schedule == null) {
            throw new TicketOperationException("Schedule not found: " + scheduleId);
        }
        return schedule;
    }

    public static void validateScheduleActive(MovieSchedule schedule, LocalDate date) {
        if (!schedule.isActive(date)) {
            throw new TicketOperationException("Movie is not showing on this date: " + date);
        }
    }

    public static void validateHallCapacity(MovieSchedule schedule, LocalDate date, Map<UUID, Ticket> soldTickets) {
        long ticketsSoldForSession = soldTickets.values()
                .stream()
                .filter(t -> t.schedule().id().equals(schedule.id()))
                .filter(t -> t.date().equals(date))
                .count();

        if (ticketsSoldForSession >= schedule.hall().capacity()) {
            throw new TicketOperationException("Hall is full for this session.");
        }
    }

    public static Ticket requireTicket(Map<UUID, Ticket> soldTickets, UUID ticketId) {
        Ticket ticket = soldTickets.get(ticketId);
        if (ticket == null) {
            throw new TicketOperationException("Ticket not found: " + ticketId);
        }
        return ticket;
    }
}