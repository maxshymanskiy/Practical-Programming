package com.theatre.app.service.impl;

import static com.theatre.app.util.validation.ModelValidator.*;

import com.theatre.app.model.ShopTransaction;
import com.theatre.app.model.TransactionType;
import com.theatre.app.model.Ticket;
import com.theatre.app.service.CinemaService;
import com.theatre.app.service.ReportService;
import com.theatre.app.service.ShopService;
import com.theatre.app.view.CinemaView;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class ReportServiceImpl implements ReportService {
    private final CinemaService cinemaService;
    private final ShopService shopService;
    private final CinemaView view;

    public ReportServiceImpl(CinemaService cinemaService, ShopService shopService, CinemaView view) {
        validateNotNull(cinemaService, "CinemaService");
        validateNotNull(shopService, "ShopService");
        validateNotNull(view, "CinemaView");
        this.cinemaService = cinemaService;
        this.shopService = shopService;
        this.view = view;
    }

    @Override
    public void report(LocalDate date) {
        validateNotNull(date, "Date");

        List<Ticket> tickets = cinemaService.getSoldTicketsForDay(date);
        double ticketRevenue = calculateRevenue(tickets);

        List<ShopTransaction> shopSales = getShopSalesForDay(date);
        double shopRevenue = calculateShopRevenue(shopSales);

        view.printDailyReport(date, tickets.size(), ticketRevenue, shopSales.size(), shopRevenue);
    }

    @Override
    public void printSchedule(LocalDate date) {
        validateNotNull(date, "Date");
        view.printSchedule(date, cinemaService.getSchedulesForDay(date));
    }

    private double calculateRevenue(List<Ticket> tickets) {
        return tickets
                .stream()
                .mapToDouble(Ticket::price)
                .sum();
    }

    private double calculateShopRevenue(List<ShopTransaction> transactions) {
        return transactions
                .stream()
                .mapToDouble(t -> t.product().price() * t.quantity())
                .sum();
    }

    private List<ShopTransaction> getShopSalesForDay(LocalDate date) {
        return shopService.getTransactions()
                .stream()
                .filter(t ->
                        t.type() == TransactionType.SELL_OUT
                        && t.timestamp().toLocalDate().equals(date)
                )
                .toList();
    }
}