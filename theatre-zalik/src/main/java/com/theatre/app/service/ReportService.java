package com.theatre.app.service;

import java.time.LocalDate;

public interface ReportService {

    void printDailyReport(LocalDate date);

    void printSchedule(LocalDate date);
}

