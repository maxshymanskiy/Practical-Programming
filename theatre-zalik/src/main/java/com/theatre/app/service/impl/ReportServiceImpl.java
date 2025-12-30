package com.theatre.app.service.impl;

import com.theatre.app.model.MovieSchedule;
import com.theatre.app.model.ShopTransaction;
import com.theatre.app.model.ShopTransaction.TransactionType;
import com.theatre.app.model.Ticket;
import com.theatre.app.service.CinemaService;
import com.theatre.app.service.ReportService;
import com.theatre.app.service.ShopService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Implementation of report service for generating daily reports.
 * Depends on abstractions (interfaces) following Dependency Inversion Principle.
 */
public class ReportServiceImpl implements ReportService {
    private final CinemaService cinemaService;
    private final ShopService shopService;

    public ReportServiceImpl(CinemaService cinemaService, ShopService shopService) {
        this.cinemaService = cinemaService;
        this.shopService = shopService;
    }

    @Override
    public void printDailyReport(LocalDate date) {
        System.out.println("=== Daily Report for " + date + " ===");

        List<Ticket> tickets = cinemaService.getSoldTicketsForDay(date);
        BigDecimal ticketRevenue = calculateTicketRevenue(tickets);
        printTicketReport(tickets.size(), ticketRevenue);

        List<ShopTransaction> shopSales = getShopSalesForDay(date);
        BigDecimal shopRevenue = calculateShopRevenue(shopSales);
        printShopReport(shopSales.size(), shopRevenue);

        System.out.println("Total Revenue: $" + ticketRevenue.add(shopRevenue));
        System.out.println("=================================");
    }

    @Override
    public void printSchedule(LocalDate date) {
        System.out.println("=== Movie Schedule for " + date + " ===");
        List<MovieSchedule> schedules = cinemaService.getSchedulesForDay(date);

        if (schedules.isEmpty()) {
            System.out.println("No movies scheduled for today.");
        } else {
            schedules.forEach(this::printScheduleItem);
        }
        System.out.println("=================================");
    }

    private BigDecimal calculateTicketRevenue(List<Ticket> tickets) {
        return tickets.stream()
                .map(Ticket::price)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateShopRevenue(List<ShopTransaction> transactions) {
        return transactions.stream()
                .map(t -> t.product().price().multiply(BigDecimal.valueOf(t.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<ShopTransaction> getShopSalesForDay(LocalDate date) {
        return shopService.getTransactions().stream()
                .filter(t -> t.timestamp().toLocalDate().equals(date))
                .filter(t -> t.type() == TransactionType.SELL_OUT)
                .toList();
    }

    private void printTicketReport(int count, BigDecimal revenue) {
        System.out.println("Tickets sold: " + count);
        System.out.println("Ticket Revenue: $" + revenue);
    }

    private void printShopReport(int count, BigDecimal revenue) {
        System.out.println("Shop Transactions: " + count);
        System.out.println("Shop Revenue: $" + revenue);
    }

    private void printScheduleItem(MovieSchedule schedule) {
        System.out.printf(
                "Time: %s | Movie: %s | Hall: %s (%s) | Price: $%s%n",
                schedule.startTime(),
                schedule.movie().title(),
                schedule.hall().name(),
                schedule.hall().type(),
                schedule.price()
        );
    }
}

