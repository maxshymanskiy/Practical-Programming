package edu.java;

import edu.java.exception.*;
import edu.java.model.*;
import edu.java.service.CourseService;
import edu.java.service.GradeService;
import edu.java.service.StudentService;

import java.time.LocalDate;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        // 1. Create services (Logic)
        final CourseService courseService = new CourseService();
        final StudentService studentService = new StudentService();
        final GradeService gradeService = new GradeService();

        System.out.println("--- 1. Creating Courses with Different Grading Formulas ---");

        // 2. Create Course #1 (CS101)
        final Course cs101 = courseService.createCourse(
                "CS101",
                "Introduction to Computer Science",
                40,
                60
        );
        System.out.println("Created course: " + cs101.getCourseName() + " (Formula: 40L + 60E)");

        // 3. Create Course #2 (MATH202)
        final Course math202 = courseService.createCourse(
                "MATH202",
                "Calculus II",
                70,
                30
        );
        System.out.println("Created course: " + math202.getCourseName() + " (Formula: 70L + 30E)");

        System.out.println("\n--- 2. Populating Course 'CS101' ---");

        // 4. Create LabWork for CS101
        final LocalDate deadline = LocalDate.now().plusDays(10);
        final LabWork lab1 = new LabWork("CS101-L1", "Data Types", 10, deadline);
        final LabWork lab2 = new LabWork("CS101-L2", "Loops", 10, deadline.plusDays(7));
        final LabWork lab3 = new LabWork("CS101-L3", "Methods", 10, deadline.plusDays(14), 2);
        final LabWork lab4 = new LabWork("CS101-L4", "Arrays", 10, deadline.plusDays(21));

        cs101.addLabWork(lab1);
        cs101.addLabWork(lab2);
        cs101.addLabWork(lab3);
        cs101.addLabWork(lab4);
        System.out.println("Added 4 lab works to " + cs101.getCourseId());

        // 5. Create Exam for CS101
        final Exam finalExam = new Exam("CS101-E1", "Final Exam", 60);

        // 6. Create Tasks for the Exam
        finalExam.addTask(new Task("T1", "Task about variables", 20));
        finalExam.addTask(new Task("T2", "Task about algorithms", 40));

        cs101.addExam(finalExam);
        System.out.println("Added 1 exam (for " + finalExam.getMaxPoints() + " points) to " + cs101.getCourseId());

        System.out.println("\n--- 3. Creating and Enrolling Students ---");

        // 7. Create students
        final Student student1 = studentService.createStudent("S001", "Ivan Petrenko", "ivan@example.com");
        final Student student2 = studentService.createStudent("S002", "Maria Nazarenko", "maria@example.com");

        // 8. Add students to the CS101 course
        cs101.addStudent(student1);
        cs101.addStudent(student2);
        System.out.println("Added students '" + student1.getName() + "' and '" + student2.getName() + "' to the CS101 course.");

        // 9. Students submit work and get grades
        student1.submitLab(lab1, 10);
        student1.submitLab(lab2, 10);
        student1.submitLab(lab3, 10);
        student1.submitLab(lab4, 10);
        student1.takeExam(finalExam, 55);

        student2.submitLab(lab1, 8);
        student2.submitLab(lab2, 7);
        student2.submitLab(lab4, 9);
        student2.takeExam(finalExam, 40);

        System.out.println("Students have received grades...");

        System.out.println("\n--- 4. Reviewing the Grade Journal (CS101) ---");

        // 10. Generate the journal
        final Map<Student, Integer> journal = gradeService.generateCourseJournal(cs101);

        journal.forEach((student, totalPoints) ->
                System.out.printf("Student: %-20s | Total Points: %-3d | Grade: %s%n",
                student.getName(),
                totalPoints,
                gradeService.getGradeLetter(totalPoints)
        ));

        System.out.println("\n--- 5. Demonstrating Penalty Calculation (Separately) ---");

        // 11. Check deadline penalty
        final LocalDate lateSubmissionDate = lab1.deadline().plusDays(1);
        final int pointsWithPenalty = gradeService.calculateLabWithPenalty(student2, lab1, lateSubmissionDate);

        System.out.println("Calculation for " + student2.getName() + " for " + lab1.title() + ":");
        System.out.println("  Original points: " + student2.getLabSubmissions().get(lab1));
        System.out.println("  Submission date: " + lateSubmissionDate + " (Deadline: " + lab1.deadline() + ")");
        System.out.println("  Points with penalty: " + pointsWithPenalty);

        System.out.println("\n--- 6. Handling Exceptional Situations (Exceptions) ---");

        // 12. Attempt to create a duplicate course
        try {
            System.out.print("Attempting to create duplicate course 'CS101': ");
            courseService.createCourse("CS101", "Duplicate Course", 10, 10);
        } catch (DuplicateCourseException e) {
            System.out.println("CAUGHT! -> " + e.getMessage());
        }

        // 13. Attempt to create inaccurate formula
        try {
            System.out.print("Attempting to add an 'extra' lab (10 points) to CS101: ");
            final LabWork extraLab = new LabWork("CS101-L5", "Extra", 10, deadline);
            cs101.addLabWork(extraLab);
        } catch (GradingFormulaException e) {
            System.out.println("CAUGHT! (Grading Formula Error) -> " + e.getMessage());
        }

        // 14. Grade out of bounds
        try {
            System.out.print("Attempting to give Ivan 15 points for a 10-point lab: ");
            student1.submitLab(lab1, 15);
        } catch (GradeOutOfBoundsException e) {
            System.out.println("CAUGHT! (Grade Out of Bounds) -> " + e.getMessage());
        }

        // 15. Attempt to find a non-existent course
        try {
            System.out.print("Attempting to find non-existent course 'CS999': ");
            courseService.getCourse("CS999");
        } catch (EntityNotFoundException e) {
            System.out.println("CAUGHT! -> " + e.getMessage());
        }
    }
}