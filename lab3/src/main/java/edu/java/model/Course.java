package edu.java.model;

import edu.java.service.ValidationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Course {
    private final String courseId;
    private final String courseName;
    private final int maxTotalLabPoints;
    private final int maxTotalExamPoints;
    private final List<LabWork> labWorks;
    private final List<Exam> exams;
    private final List<Student> students;

    public Course(String courseId, String courseName, int maxTotalLabPoints, int maxTotalExamPoints) {
        ValidationService.validateCourseFormula(maxTotalLabPoints, maxTotalExamPoints);

        this.courseId = courseId;
        this.courseName = courseName;
        this.maxTotalLabPoints = maxTotalLabPoints;
        this.maxTotalExamPoints = maxTotalExamPoints;
        this.labWorks = new ArrayList<>();
        this.exams = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public void addLabWork(LabWork labWork) {
        ValidationService.validateCourseAfterAddingLab(this, labWork);
        labWorks.add(labWork);
    }

    public void addExam(Exam exam) {
        ValidationService.validateCourseAfterAddingExam(this, exam);
        exams.add(exam);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getMaxTotalLabPoints() {
        return maxTotalLabPoints;
    }

    public int getMaxTotalExamPoints() {
        return maxTotalExamPoints;
    }

    public List<LabWork> getLabWorks() {
        return new ArrayList<>(labWorks);
    }

    public List<Exam> getExams() {
        return new ArrayList<>(exams);
    }

    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }

    public int getMaxPoints() {
        return GradingConstants.MAX_TOTAL_POINTS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(courseId, course.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId);
    }
}