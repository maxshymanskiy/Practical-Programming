package edu.java.service;

import edu.java.exception.EntityNotFoundException;
import edu.java.model.Course;
import edu.java.model.Exam;
import edu.java.model.LabWork;
import edu.java.model.Student;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

public class StudentService {
    private Map<String, Student> students;

    public StudentService() {
        this.students = new HashMap<>();
    }

    public Student enrollStudent(Course course, String studentId, String name, String email) {
        Student student = new Student(studentId, name, email);
        students.put(studentId, student);
        course.addStudent(student);
        return student;
    }

    public void recordLabGrade(Student student, LabWork lab, double points, LocalDate submissionDate) {
        student.addLabGrade(lab.getId(), points, submissionDate);
    }

    public void recordExamGrade(Student student, Exam exam, double points) {
        student.addExamGrade(exam.getId(), points);
    }

    public Student findStudentById(String studentId) throws EntityNotFoundException {
        return students.values().stream()
                .filter(student -> student.getId().equals(studentId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Student not found: " + studentId));
    }

    public List<Student> getStudentsInCourse(Course course) {
        return course.getStudents().stream()
                .sorted((s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()))
                .collect(Collectors.toList());
    }
}
