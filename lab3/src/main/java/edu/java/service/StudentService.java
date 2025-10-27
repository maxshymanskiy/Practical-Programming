package edu.java.service;

import edu.java.exception.EntityNotFoundException;
import edu.java.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentService {

    private final Map<String, Student> students = new HashMap<>();

    public StudentService() {}

    public Student createStudent(String studentId, String name, String email) {
        Student student = new Student(studentId, name, email);
        students.put(studentId, student);
        return student;
    }

    public Student getStudent(String studentId) {
        Student student = students.get(studentId);
        if (student == null) {
            throw new EntityNotFoundException("Student with ID " + studentId + " not found");
        }
        return student;
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }
}