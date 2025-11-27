package edu.java.lab4.controller;

import edu.java.lab4.dto.request.StudentCreateDto;
import edu.java.lab4.dto.response.StudentDetailResponse;
import edu.java.lab4.dto.response.StudentDto;
import edu.java.lab4.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@Valid @RequestBody StudentCreateDto request) {
        log.info("REST: Creating student {}", request.getEmail());
        StudentDto response = studentService.createStudent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        log.info("REST: Getting all students");
        List<StudentDto> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }


    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable Long id) {
        log.info("REST: Getting student {}", id);
        StudentDto response = studentService.getStudentById(id);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}/details")
    public ResponseEntity<StudentDetailResponse> getStudentDetails(@PathVariable Long id) {
        log.info("REST: Getting student details {}", id);
        StudentDetailResponse response = studentService.getStudentDetails(id);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/search")
    public ResponseEntity<StudentDto> findByEmail(@RequestParam String email) {
        log.info("REST: Finding student by email {}", email);
        StudentDto response = studentService.findByEmail(email);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        log.info("REST: Deleting student {}", id);
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}