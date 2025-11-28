package edu.java.lab4.controller;

import edu.java.lab4.dto.request.*;
import edu.java.lab4.dto.response.*;
import edu.java.lab4.service.*;
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
@RequestMapping("/api/labs")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class LabWorkController {

    private final LabWorkService labWorkService;

    @PostMapping
    public ResponseEntity<LabWorkDto> createLabWork(@Valid @RequestBody LabWorkCreateDto request) {
        log.info("REST: Creating lab work {}", request.getTitle());
        LabWorkDto response = labWorkService.createLabWork(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/course/{courseId}")
    @PreAuthorize("hasRole('STUDENT') or hasRole('ADMIN')")
    public ResponseEntity<List<LabWorkDto>> getLabWorksByCourse(@PathVariable Long courseId) {
        log.info("REST: Getting labs for course {}", courseId);
        List<LabWorkDto> labs = labWorkService.getLabWorksByCourse(courseId);
        return ResponseEntity.ok(labs);
    }


    @GetMapping("/{id}")
    public ResponseEntity<LabWorkDto> getLabWork(@PathVariable Long id) {
        log.info("REST: Getting lab work {}", id);
        LabWorkDto response = labWorkService.getLabWorkById(id);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public void deleteLabWork(@PathVariable Long id) {
        log.info("REST: Deleting lab work {}", id);
        labWorkService.deleteLabWork(id);
    }


    @PostMapping("/submit")
    @PreAuthorize("hasRole('STUDENT') or hasRole('ADMIN')")
    public ResponseEntity<LabSubmissionDto> submitLabWork(@Valid @RequestBody LabSubmissionCreateDto request) {
        log.info("REST: Submitting lab work {} for student {}", request.getLabWorkId(), request.getStudentId());
        LabSubmissionDto response = labWorkService.submitLabWork(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PostMapping("/grade")
    public ResponseEntity<LabSubmissionDto> gradeLabSubmission(@Valid @RequestBody LabGradeEvaluateDto request) {
        log.info("REST: Grading lab submission {}", request.getSubmissionId());
        LabSubmissionDto response = labWorkService.gradeLabSubmission(request);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{labId}/submissions")
    @PreAuthorize("hasRole('STUDENT') or hasRole('ADMIN')")
    public ResponseEntity<List<LabSubmissionDto>> getLabSubmissions(@PathVariable Long labId) {
        log.info("REST: Getting submissions for lab {}", labId);
        List<LabSubmissionDto> submissions = labWorkService.getSubmissionsByLabWork(labId);
        return ResponseEntity.ok(submissions);
    }
}