package com.theatre.app.service;

import java.time.LocalDate;

/**
 * Service for generating reports about cinema and shop operations.
 */
public interface ReportService {
    void printDailyReport(LocalDate date);
    void printSchedule(LocalDate date);
}

