package com.theatre.app.view;

import com.theatre.app.model.MovieSchedule;

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

    public void printDailyReport(LocalDate date, int ticketCount, double ticketRevenue, int shopCount, double shopRevenue) {
        System.out.println("=== Daily Report for " + date + " ===");
        System.out.println("Tickets sold: " + ticketCount);
        System.out.printf("Ticket Revenue: $%.2f%n", ticketRevenue);
        System.out.println("Shop Transactions: " + shopCount);
        System.out.printf("Shop Revenue: $%.2f%n", shopRevenue);
        System.out.printf("Total Revenue: $%.2f%n", (ticketRevenue + shopRevenue));
        System.out.println("=================================");
    }

    public void printSchedule(LocalDate date, List<MovieSchedule> schedules) {
        System.out.println("=== Movie Schedule for " + date + " ===");
        if (schedules.isEmpty()) {
            System.out.println("No movies scheduled for today.");
        } else {
            schedules.forEach(s -> System.out.printf(
                    "Time: %s | Movie: %s | Hall: %s (%s) | Price: $%.2f%n",
                    s.startTime(), s.movie().title(), s.hall().name(), s.hall().type(), s.price()
            ));
        }
        System.out.println("=================================");
    }
}