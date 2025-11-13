package edu.java.lab4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableScheduling
@EnableAsync
@SpringBootApplication
public class Lab4Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab4Application.class, args);
    }



    // JPQL queries //

    //=== Project improvements ===//

    /*
     - Convert Constants to Enum where applicable (previous lab)
     - Add logging data to the file
     - Add Spring Security for authentication and authorization
     - Write unit tests for the project
     */


    /* ERRORS to fix
    [X] Get Course Details - getting detail when students are not enrolled in a course and/or the labTask are not assigned
    [X] Get Course Journal - 500 error, when don't exist --- FIXED
    [X] Grade Lab Submission and Grade Exam Submission - teacher can give a higher grade than labs can take (labWeight = 10, assigned 15)
    [X] Get Student Grade - return 0 if a student is not enrolled in a course
     */




    /*
    Так, після видалення LEFT JOIN FETCH c.exams ви все одно
     зможете отримати дані про екзамен у цьому запиті (ендпоінті), бо
    calculateCourseJournal() позначений @Transactional(readOnly = true) і
    доступ до course.getExams() відбудеться всередині тієї ж транзакції
    — Hibernate підвантажить їх ліниво (LAZY) без помилок.
     */



}




