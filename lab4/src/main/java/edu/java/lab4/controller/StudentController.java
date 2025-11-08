package edu.java.lab4.controller;

import edu.java.lab4.dto.request.StudentCreateRequest;
import edu.java.lab4.dto.response.StudentDetailResponse;
import edu.java.lab4.dto.response.StudentResponse;
import edu.java.lab4.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@Valid @RequestBody StudentCreateRequest request) {
        log.info("REST: Creating student {}", request.getEmail());
        StudentResponse response = studentService.createStudent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        log.info("REST: Getting all students");
        List<StudentResponse> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }


    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudent(@PathVariable Long id) {
        log.info("REST: Getting student {}", id);
        StudentResponse response = studentService.getStudentById(id);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}/details")
    public ResponseEntity<StudentDetailResponse> getStudentDetails(@PathVariable Long id) {
        log.info("REST: Getting student details {}", id);
        StudentDetailResponse response = studentService.getStudentDetails(id);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/search")
    public ResponseEntity<StudentResponse> findByEmail(@RequestParam String email) {
        log.info("REST: Finding student by email {}", email);
        StudentResponse response = studentService.findByEmail(email);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        log.info("REST: Deleting student {}", id);
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}