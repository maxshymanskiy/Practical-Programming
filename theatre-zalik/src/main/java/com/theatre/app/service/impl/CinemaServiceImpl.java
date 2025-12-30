package com.theatre.app.service.impl;

import static com.theatre.app.validation.CinemaValidator.*;

import com.theatre.app.model.MovieSchedule;
import com.theatre.app.model.Ticket;
import com.theatre.app.service.CinemaService;

import java.time.LocalDate;
import java.util.*;


public class CinemaServiceImpl implements CinemaService {
    private final Map<String, MovieSchedule> schedules = new HashMap<>();
    private final Map<UUID, Ticket> soldTickets = new HashMap<>();

    @Override
    public void addMovieSchedule(MovieSchedule schedule) {
        schedules.put(schedule.id(), schedule);
    }

    @Override
    public void removeMovieSchedule(String scheduleId) {
        schedules.remove(scheduleId);
    }

    @Override
    public Ticket buyTicket(String scheduleId, LocalDate date) {
        MovieSchedule schedule = requireSchedule(schedules, scheduleId);
        validateScheduleActive(schedule, date);
        validateHallCapacity(schedule, date, soldTickets);

        Ticket ticket = new Ticket(
                UUID.randomUUID(),
                schedule,
                date,
                schedule.price()
        );

        soldTickets.put(ticket.id(), ticket);
        return ticket;
    }

    @Override
    public void returnTicket(UUID ticketId) {
        Ticket ticket = requireTicket(soldTickets, ticketId);
        soldTickets.remove(ticket.id());
    }

    @Override
    public List<MovieSchedule> getSchedulesForDay(LocalDate date) {
        return schedules.values()
                .stream()
                .filter(schedule -> schedule.isActive(date))
                .sorted(Comparator.comparing(MovieSchedule::startTime))
                .toList();
    }

    @Override
    public List<Ticket> getSoldTicketsForDay(LocalDate date) {
        return soldTickets.values()
                .stream()
                .filter(ticket -> ticket.date().equals(date))
                .toList();
    }
}

