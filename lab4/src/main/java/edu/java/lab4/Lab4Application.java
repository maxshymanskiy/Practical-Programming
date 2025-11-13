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
     1) [FAILED] Convert Constants to Enum where applicable (previous lab)
     -> hard to do, tried.
     -> problems with ```enum.value()``` in some @Annotations (Type Object in return)

     2) [DONE] Add flyway for db migrations.
      -> connected and stored in data/

     3) [DONE] Add logging data to the file.
      -> ```private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MyService.class);```
      -> automatically added by Lombok
       -> saved in log/

     4) [DONE/kind of :/] Add Spring Security for authentication and authorization.
     -> need to add TEACHER for registration (now only STUDENT and ADMIN). Fix the logic problem of registration,
        because now only ADMIN can register users.
     -> Better solution: make for unauthorized users the opportunity to provide their credentials and then
        send a request to ADMIN to register them.

     5) [IN PROGRESS] Write unit tests for the project.


     */


    /* ERRORS to fix
    [X] Get Course Details - getting detail when students are not enrolled in a course and/or the labTask is not assigned --- FIXED
    [X] Get Course Journal - 500 error, when don't exist --- FIXED
    [X] Grade Lab Submission and Grade Exam Submission - teacher can give a higher grade than labs can take (labWeight = 10, assigned 15) --- FIXED
    [X] Get Student Grade - return 0 if a student is not enrolled in a course --- FIXED
    [] Creating labwork when my limitation is 3, but I can create 4
     */

    /*
    - error with @Transactional and @Lazy - circular dependency(FIXED) in StudentMapper and CourseMapper
    - InvalidGradeException - not handle by global exception handler (FIXED)
     */
}




