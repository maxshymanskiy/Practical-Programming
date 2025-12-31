package com.theatre.app;

import com.theatre.app.service.*;
import com.theatre.app.service.impl.*;
import com.theatre.app.util.DataInitializer;
import com.theatre.app.view.CinemaView;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        CinemaService cinemaService = new CinemaServiceImpl();
        ShopService shopService = new ShopServiceImpl();
        CinemaView view = new CinemaView();
        ReportService reportService = new ReportServiceImpl(cinemaService, shopService, view);

        // Initialize data
        DataInitializer.initializeCinema(cinemaService);
        DataInitializer.initializeShop(shopService);

        LocalDate today = LocalDate.now();

        // Show schedule
        view.printSection("Today's Schedule");
        reportService.printSchedule(today);

        // Buy tickets
        view.printSection("Buying Tickets");
        var ticket1 = cinemaService.buyTicket("S1", today);
        view.printMessage("Bought ticket: " + ticket1.id());

        var ticket2 = cinemaService.buyTicket("S2", today);
        view.printMessage("Bought ticket: " + ticket2.id());

        // Return ticket
        view.printSection("Returning Ticket");
        cinemaService.returnTicket(ticket1.id());
        view.printMessage("Returned ticket: " + ticket1.id());

        // Shop operations
        view.printSection("Shop Operations");
        shopService.sellProduct("P1", 5);
        view.printMessage("Sold 5 Popcorn");

        shopService.sellProduct("P2", 10);
        view.printMessage("Sold 10 Soda");

        shopService.restockProduct("P1", 20);
        view.printMessage("Restocked 20 Popcorn");

        // Show reports
        view.printSection("Daily Report");
        reportService.report(today);

        // Test validation
        view.printSection("Testing Validations");
        DataInitializer.testValidations(cinemaService, shopService, view);

        view.printSection("Done!");
    }
}