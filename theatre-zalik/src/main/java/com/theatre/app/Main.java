package com.theatre.app;

import com.theatre.app.model.*;
import com.theatre.app.service.*;
import com.theatre.app.service.impl.*;
import com.theatre.app.view.CinemaView;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {

        CinemaService cinemaService = new CinemaServiceImpl();
        ShopService shopService = new ShopServiceImpl();
        CinemaView view = new CinemaView();
        ReportService reportService = new ReportServiceImpl(cinemaService, shopService, view);

        // Filling with test/mock data
        setupCinema(cinemaService);
        setupShop(shopService);

        LocalDate today = LocalDate.now();

        view.printSection("Morning Schedule");
        reportService.printSchedule(today);

        view.printSection("Trying to buy tickets");
        try {
            cinemaService.buyTicket("S1", today);
            cinemaService.buyTicket("S1", today);
            cinemaService.buyTicket("S2", today);
            view.printMessage("Tickets purchased successfully.");
        } catch (Exception e) {
            view.printError(e.getMessage());
        }

        view.printSection("Trying shop operations");
        try {
            shopService.sellProduct("P1", 5);
            shopService.sellProduct("P2", 2);
            view.printMessage("Products sold successfully.");
        } catch (Exception e) {
            view.printError(e.getMessage());
        }

        view.printSection("End of Day Report");
        reportService.printDailyReport(today);

    }

    private static void setupCinema(CinemaService cinemaService) {
        Hall hall1 = new Hall("H1", "Main Hall", HallType.NORMAL, 100);
        Hall hall2 = new Hall("H2", "4D Experience", HallType.FOUR_D, 50);

        Movie movie1 = new Movie("M1", "Inception", Duration.ofMinutes(148));
        Movie movie2 = new Movie("M2", "Avatar 2", Duration.ofMinutes(192));

        LocalDate today = LocalDate.now();
        LocalDate nextWeek = today.plusDays(7);

        MovieSchedule schedule1 = new MovieSchedule(
                "S1", movie1, hall1, LocalTime.of(14, 0), new BigDecimal("15.00"), today, nextWeek
        );
        MovieSchedule schedule2 = new MovieSchedule(
                "S2", movie2, hall2, LocalTime.of(18, 0), new BigDecimal("25.00"), today, nextWeek
        );

        cinemaService.addMovieSchedule(schedule1);
        cinemaService.addMovieSchedule(schedule2);
    }

    private static void setupShop(ShopService shopService) {
        Product popcorn = new Product("P1", "Large Popcorn", new BigDecimal("8.50"));
        Product soda = new Product("P2", "Soda", new BigDecimal("4.00"));

        shopService.addProduct(popcorn);
        shopService.addProduct(soda);

        shopService.restockProduct("P1", 50);
        shopService.restockProduct("P2", 100);
    }
}