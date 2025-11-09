package edu.java.lab4.service;

import edu.java.lab4.dto.request.StudentCreateRequest;
import edu.java.lab4.dto.response.StudentDetailResponse;
import edu.java.lab4.dto.response.StudentResponse;
import java.util.List;

public interface StudentService {

    StudentResponse createStudent(StudentCreateRequest request);

    StudentResponse getStudentById(Long id);

    StudentDetailResponse getStudentDetails(Long id);

    List<StudentResponse> getAllStudents();

    StudentResponse findByEmail(String email);

    StudentResponse findByStudentNumber(String studentNumber);

    void deleteStudent(Long id);
}