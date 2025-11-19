package edu.java.lab4.service;

import edu.java.lab4.dto.request.StudentCreateDto;
import edu.java.lab4.dto.response.StudentDetailResponse;
import edu.java.lab4.dto.response.StudentDto;
import java.util.List;

public interface StudentService {

    StudentDto createStudent(StudentCreateDto request);

    StudentDto getStudentById(Long id);

    StudentDetailResponse getStudentDetails(Long id);

    List<StudentDto> getAllStudents();

    StudentDto findByEmail(String email);

    StudentDto findByStudentNumber(String studentNumber);

    void deleteStudent(Long id);
}