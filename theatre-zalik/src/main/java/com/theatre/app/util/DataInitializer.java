package com.theatre.app.util;

import com.theatre.app.exception.InventoryException;
import com.theatre.app.exception.TicketOperationException;
import com.theatre.app.model.*;
import com.theatre.app.service.CinemaService;
import com.theatre.app.service.ShopService;
import com.theatre.app.view.CinemaView;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public final class DataInitializer {
    private DataInitializer() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static void initializeCinema(CinemaService cinemaService) {
        Hall hall1 = new Hall("H1", "Main Hall", HallType.NORMAL, 100);
        Hall hall2 = new Hall("H2", "4D Hall", HallType.FOUR_D, 50);

        Movie movie1 = new Movie("M1", "Inception", Duration.ofMinutes(148));
        Movie movie2 = new Movie("M2", "Avatar 2", Duration.ofMinutes(192));

        LocalDate today = LocalDate.now();
        LocalDate nextWeek = today.plusDays(7);

        cinemaService.addMovieSchedule(new MovieSchedule(
                "S1", movie1, hall1, LocalTime.of(14, 0), 15.00, today, nextWeek));
        cinemaService.addMovieSchedule(new MovieSchedule(
                "S2", movie2, hall2, LocalTime.of(18, 0), 25.00, today, nextWeek));
        cinemaService.addMovieSchedule(new MovieSchedule(
                "S3", movie1, hall2, LocalTime.of(21, 0), 20.00, today, nextWeek));
    }

    public static void initializeShop(ShopService shopService) {
        shopService.addProduct(new Product("P1", "Popcorn", 8.50));
        shopService.addProduct(new Product("P2", "Soda", 4.00));
        shopService.addProduct(new Product("P3", "Candy", 3.50));

        shopService.restockProduct("P1", 50);
        shopService.restockProduct("P2", 100);
        shopService.restockProduct("P3", 75);
    }

    public static void testValidations(CinemaService cinemaService, ShopService shopService, CinemaView view) {
        // Test 1: Invalid schedule ID
        try {
            cinemaService.buyTicket("INVALID", LocalDate.now());
            view.printError("FAIL: Should have thrown TicketOperationException");
        } catch (TicketOperationException e) {
            view.printMessage("PASS: " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }

        // Test 2: Past date
        try {
            cinemaService.buyTicket("S1", LocalDate.now().minusDays(10));
            view.printError("FAIL: Should have thrown TicketOperationException");
        } catch (TicketOperationException e) {
            view.printMessage("PASS: " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }

        // Test 3: Invalid ticket ID
        try {
            cinemaService.returnTicket("INVALID-TICKET");
            view.printError("FAIL: Should have thrown TicketOperationException");
        } catch (TicketOperationException e) {
            view.printMessage("PASS: " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }

        // Test 4: Duplicate schedule
        try {
            cinemaService.addMovieSchedule(new MovieSchedule(
                    "S1",
                    new Movie("M1", "Test", Duration.ofMinutes(120)),
                    new Hall("H1", "Test", HallType.NORMAL, 50),
                    LocalTime.of(10, 0), 10.00,
                    LocalDate.now(), LocalDate.now().plusDays(7)));
            view.printError("FAIL: Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            view.printMessage("PASS: " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }

        // Test 5: Insufficient stock
        try {
            shopService.sellProduct("P1", 1000);
            view.printError("FAIL: Should have thrown InventoryException");
        } catch (InventoryException e) {
            view.printMessage("PASS: " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }

        // Test 6: Invalid product ID
        try {
            shopService.sellProduct("INVALID", 1);
            view.printError("FAIL: Should have thrown InventoryException");
        } catch (InventoryException e) {
            view.printMessage("PASS: " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }

        // Test 7: Negative quantity
        try {
            shopService.restockProduct("P1", -10);
            view.printError("FAIL: Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            view.printMessage("PASS: " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }

        // Test 8: Duplicate product
        try {
            shopService.addProduct(new Product("P1", "Duplicate", 5.00));
            view.printError("FAIL: Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            view.printMessage("PASS: " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }
}