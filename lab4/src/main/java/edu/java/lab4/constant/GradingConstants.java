package edu.java.lab4.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GradingConstants {

    MAX_TOTAL_POINTS(100, "Max total points"),
    EXCELLENT_MIN(88, "Excellent"),
    GOOD_MIN(71, "Good"),
    SATISFACTORY_MIN(51, "Satisfactory"),
    DEFAULT_PENALTY_PER_DAY(1, "Default penalty per day"),
    FAIL(50, "Fail"),
    ;

    private final int value;
    private final String description;
}