package edu.java.lab4.constant;

public final class GradingConstants {
    private GradingConstants() {}

    public static final int DEFAULT_LAB_WEIGHT = 10;
    public static final int DEFAULT_EXAM_WEIGHT = 60;
    public static final int DEFAULT_LAB_COUNT = 4;

    public static final double LATE_SUBMISSION_PENALTY_PERCENT = 0.10; // 10% penalty per day
    public static final int MAX_LATE_DAYS = 7; // Maximum days allowed for late submission

    public static final int MAX_GRADE = 100;
    public static final int PASSING_GRADE = 50;

    public static final int MIN_COURSE_NAME_LENGTH = 3;
    public static final int MAX_COURSE_NAME_LENGTH = 100;
    public static final int MIN_TASK_DESCRIPTION_LENGTH = 10;
    public static final int MAX_TASK_DESCRIPTION_LENGTH = 1000;

    public static final String CACHE_COURSES = "courses";
    public static final String CACHE_STUDENTS = "students";
    public static final String CACHE_JOURNAL = "journal";
}