package com.theatre.app.view;

import com.theatre.app.model.MovieSchedule;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class CinemaView {

    public void printSection(String title) {
        System.out.println("\n--- " + title + " ---");
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printError(String message) {
        System.err.println("Error: " + message);
    }

    public void printDailyReport(LocalDate date, int ticketCount, BigDecimal ticketRevenue, int shopCount, BigDecimal shopRevenue) {
        System.out.println("=== Daily Report for " + date + " ===");
        System.out.println("Tickets sold: " + ticketCount);
        System.out.println("Ticket Revenue: $" + ticketRevenue);
        System.out.println("Shop Transactions: " + shopCount);
        System.out.println("Shop Revenue: $" + shopRevenue);
        System.out.println("Total Revenue: $" + ticketRevenue.add(shopRevenue));
        System.out.println("=================================");
    }

    public void printSchedule(LocalDate date, List<MovieSchedule> schedules) {
        System.out.println("=== Movie Schedule for " + date + " ===");
        if (schedules.isEmpty()) {
            System.out.println("No movies scheduled for today.");
        } else {
            schedules.forEach(s -> System.out.printf(
                    "Time: %s | Movie: %s | Hall: %s (%s) | Price: $%s%n",
                    s.startTime(), s.movie().title(), s.hall().name(), s.hall().type(), s.price()
            ));
        }
        System.out.println("=================================");
    }
}

