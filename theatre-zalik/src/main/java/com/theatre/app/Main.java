package com.theatre.app;

import com.theatre.app.model.*;
import com.theatre.app.service.*;
import com.theatre.app.service.impl.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        // Initialize Services (Program to interface, not implementation)
        CinemaService cinemaService = new CinemaServiceImpl();
        ShopService shopService = new ShopServiceImpl();
        ReportService reportService = new ReportServiceImpl(cinemaService, shopService);

        // Setup Data
        setupCinema(cinemaService);
        setupShop(shopService);

        // Simulate Operations
        LocalDate today = LocalDate.now();

        System.out.println("\n--- Morning Schedule ---");
        reportService.printSchedule(today);

        // Buy tickets
        System.out.println("\n--- Buying Tickets ---");
        try {
            cinemaService.buyTicket("S1", today);
            cinemaService.buyTicket("S1", today);
            cinemaService.buyTicket("S2", today);
            System.out.println("Tickets purchased successfully.");
        } catch (Exception e) {
            System.err.println("Error buying ticket: " + e.getMessage());
        }

        // Shop operations
        System.out.println("\n--- Shop Operations ---");
        try {
            shopService.sellProduct("P1", 5); // Popcorn
            shopService.sellProduct("P2", 2); // Soda
            System.out.println("Products sold successfully.");
        } catch (Exception e) {
            System.err.println("Error in shop: " + e.getMessage());
        }

        // End of day report
        System.out.println("\n--- End of Day Report ---");
        reportService.printDailyReport(today);
    }

    private static void setupCinema(CinemaService cinemaService) {
        // Create Halls
        Hall hall1 = new Hall("H1", "Main Hall", HallType.NORMAL, 100);
        Hall hall2 = new Hall("H2", "4D Experience", HallType.FOUR_D, 50);

        // Create Movies
        Movie movie1 = new Movie("M1", "Inception", Duration.ofMinutes(148));
        Movie movie2 = new Movie("M2", "Avatar 2", Duration.ofMinutes(192));

        // Create Schedules
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

        // Initial Stock
        shopService.restockProduct("P1", 50);
        shopService.restockProduct("P2", 100);
    }
}


/*
 * Теорія
 * 1. (5 б). Що таке Set та Map? Яка між ними різниця? Чи є у них щось спільне? Продемонструйте їхнє використання.
 * 2. (5 б). Що REST API? Яке його призначення? Наведіть приклад
 *
 *
 * Практика
 * 1 (35 б). Реалізуйте ієрархію класів для моделювання роботи кінотеатру.
 * Кінотеатр має декілька видів залів (звичайні та 4D) і власний магазин із продажу їжі.
 * Необхідно змоделювати систему роботи кінотеатру: створення нового фільму (початок прокату), зняття фільму з прокату,
 * купівля квитків, повернення квитків, облік товарів (купівля і продаж) із магазину кінотеатру
 * Реалізуйте клас Report з методом report() що повинен вивести на екран структуру доходів для заданого дня.
 * Створити метод який виводить усі сеанси усіх фільмів на день.
 * Створіть 2 нових Exceptions на власний розсуд та використайте їх у програмі.
 * Використовуйте Stream API де це потрібно.
 *
 * УВАГА!!! Для спрощення роботи із датами можна використовувати класи LocalDate та LocalTime, також можна припускати що
 * фільми показуються в той самий час впродовж усього прокату фільму.
 */