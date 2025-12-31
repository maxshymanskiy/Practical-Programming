package com.theatre.app.service.impl;

import static com.theatre.app.util.validation.CinemaValidator.*;
import static com.theatre.app.util.validation.ModelValidator.*;

import com.theatre.app.model.MovieSchedule;
import com.theatre.app.model.Ticket;
import com.theatre.app.service.CinemaService;

import java.time.LocalDate;
import java.util.*;


public class CinemaServiceImpl implements CinemaService {
    private final Map<String, MovieSchedule> schedules = new HashMap<>();
    private final Map<String, Ticket> soldTickets = new HashMap<>();

    @Override
    public void addMovieSchedule(MovieSchedule schedule) {
        validateNotNull(schedule, "MovieSchedule");
        if (schedules.containsKey(schedule.id())) {
            throw new IllegalArgumentException("Schedule with ID " + schedule.id() + " already exists");
        }
        schedules.put(schedule.id(), schedule);
    }

    @Override
    public void removeMovieSchedule(String scheduleId) {
        validateScheduleExists(schedules, scheduleId);

        boolean hasTickets = soldTickets.values()
                .stream()
                .anyMatch(ticket -> ticket.schedule().id().equals(scheduleId));

        if (hasTickets) {
            throw new IllegalStateException("Cannot remove schedule with sold tickets: " + scheduleId);
        }

        schedules.remove(scheduleId);
    }

    @Override
    public Ticket buyTicket(String scheduleId, LocalDate date) {
        validateNotNull(date, "Date");
        MovieSchedule schedule = validateScheduleExists(schedules, scheduleId);
        validateScheduleActive(schedule, date);
        validateHallCapacity(schedule, date, soldTickets);

        String ticketId = generateTicketId(scheduleId, date);
        Ticket ticket = new Ticket(
                ticketId,
                schedule,
                date,
                schedule.price()
        );

        soldTickets.put(ticket.id(), ticket);
        return ticket;
    }

    @Override
    public void returnTicket(String ticketId) {
        Ticket ticket = validateTicketExists(soldTickets, ticketId);
        soldTickets.remove(ticket.id());
    }

    @Override
    public List<MovieSchedule> getSchedulesForDay(LocalDate date) {
        validateNotNull(date, "Date");
        return schedules.values()
                .stream()
                .filter(schedule -> schedule.isActive(date))
                .sorted(Comparator.comparing(MovieSchedule::startTime))
                .toList();
    }

    @Override
    public List<Ticket> getSoldTicketsForDay(LocalDate date) {
        validateNotNull(date, "Date");
        return soldTickets.values()
                .stream()
                .filter(ticket -> ticket.date().equals(date))
                .toList();
    }

    private String generateTicketId(String scheduleId, LocalDate date) {
        return "T-" + scheduleId + "-" + date + "-" + System.currentTimeMillis();
    }
}

