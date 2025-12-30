package com.theatre.app.service.impl;

import com.theatre.app.model.ShopTransaction;
import com.theatre.app.model.TransactionType;
import com.theatre.app.model.Ticket;
import com.theatre.app.service.CinemaService;
import com.theatre.app.service.ReportService;
import com.theatre.app.service.ShopService;
import com.theatre.app.view.CinemaView;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ReportServiceImpl implements ReportService {
    private final CinemaService cinemaService;
    private final ShopService shopService;
    private final CinemaView view;

    public ReportServiceImpl(CinemaService cinemaService, ShopService shopService, CinemaView view) {
        this.cinemaService = cinemaService;
        this.shopService = shopService;
        this.view = view;
    }

    @Override
    public void printDailyReport(LocalDate date) {
        List<Ticket> tickets = cinemaService.getSoldTicketsForDay(date);
        BigDecimal ticketRevenue = calculateRevenue(tickets);

        List<ShopTransaction> shopSales = getShopSalesForDay(date);
        BigDecimal shopRevenue = calculateShopRevenue(shopSales);

        view.printDailyReport(date, tickets.size(), ticketRevenue, shopSales.size(), shopRevenue);
    }

    @Override
    public void printSchedule(LocalDate date) {
        view.printSchedule(date, cinemaService.getSchedulesForDay(date));
    }

    private BigDecimal calculateRevenue(List<Ticket> tickets) {
        return tickets
                .stream()
                .map(Ticket::price)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateShopRevenue(List<ShopTransaction> transactions) {
        return transactions
                .stream()
                .map(t -> t.product().price().multiply(BigDecimal.valueOf(t.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<ShopTransaction> getShopSalesForDay(LocalDate date) {
        return shopService.getTransactions()
                .stream()
                .filter(t -> t.timestamp().toLocalDate().equals(date))
                .filter(t -> t.type() == TransactionType.SELL_OUT)
                .toList();
    }
}