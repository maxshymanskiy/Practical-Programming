package com.theatre.app.service;

import java.time.LocalDate;

public interface ReportService {

    void report(LocalDate date);

    void printSchedule(LocalDate date);
}

