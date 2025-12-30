package com.theatre.app.service;

import com.theatre.app.model.MovieSchedule;
import com.theatre.app.model.Ticket;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface CinemaService {

    void addMovieSchedule(MovieSchedule schedule);

    void removeMovieSchedule(String scheduleId);

    Ticket buyTicket(String scheduleId, LocalDate date);

    void returnTicket(UUID ticketId);

    List<MovieSchedule> getSchedulesForDay(LocalDate date);

    List<Ticket> getSoldTicketsForDay(LocalDate date);
}

