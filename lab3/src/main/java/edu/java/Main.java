package edu.java;

import edu.java.service.*;
import edu.java.model.*;
import edu.java.exception.*;


import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        CourseService courseService = new CourseService();
        StudentService studentService = new StudentService();
        GradeService gradeService = new GradeService();

        try {
            System.out.println("=== Coursework Management System ===\n");

            Course course = courseService.createCourse("CS101", "Java Programming");
            System.out.println("Created course: " + course.getName());

            LabWork lab1 = courseService.addLabWork(course, "LAB1", "Basic Syntax",
                    LocalDate.now().plusDays(7));
            lab1.addTask(new Task("Write Hello World", 3.0));
            lab1.addTask(new Task("Create variables", 4.0));
            lab1.addTask(new Task("Simple arithmetic", 3.0));

            LabWork lab2 = courseService.addLabWork(course, "LAB2", "OOP Principles",
                    LocalDate.now().plusDays(14));
            lab2.addTask(new Task("Create Student class", 4.0));
            lab2.addTask(new Task("Implement encapsulation", 3.0));
            lab2.addTask(new Task("Demonstrate inheritance", 3.0));

            Exam exam = courseService.addExam(course, "EXAM1", "Final Exam",
                    LocalDate.now().plusDays(35));
            exam.addTask(new Task("Multiple choice", 20.0));
            exam.addTask(new Task("Code analysis", 20.0));
            exam.addTask(new Task("Programming problem", 20.0));
            exam.addVariant("Variant A");
            exam.addVariant("Variant B");

            System.out.println("Added labs and exam with tasks");

            Student alice = studentService.enrollStudent(course, "S001", "Alice Johnson", "alice@edu.com");
            Student bob = studentService.enrollStudent(course, "S002", "Bob Smith", "bob@edu.com");

            studentService.recordLabGrade(alice, lab1, 9.5, LocalDate.now().plusDays(5));
            studentService.recordLabGrade(alice, lab2, 8.0, LocalDate.now().plusDays(12));
            studentService.recordExamGrade(alice, exam, 55.0);

            studentService.recordLabGrade(bob, lab1, 7.0, LocalDate.now().plusDays(8));
            studentService.recordLabGrade(bob, lab2, 6.5, LocalDate.now().plusDays(16)); // спізнення
            studentService.recordExamGrade(bob, exam, 42.0);

            System.out.println("\n" + gradeService.generateCourseJournal(course));

            System.out.println("=== Grade Boundaries ===");
            double[] testScores = {95.0, 88.0, 80.0, 71.0, 65.0, 51.0, 45.0};
            for (double score : testScores) {
                System.out.printf("Score %.1f: %s\n", score, gradeService.getGradeLetter(score));
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}