package edu.java.lab4.controller;

import edu.java.lab4.dto.request.*;
import edu.java.lab4.dto.response.*;
import edu.java.lab4.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/labs")
@RequiredArgsConstructor
public class LabWorkController {

    private final LabWorkService labWorkService;

    @PostMapping
    public ResponseEntity<LabWorkResponse> createLabWork(@Valid @RequestBody LabWorkCreateRequest request) {
        log.info("REST: Creating lab work {}", request.getTitle());
        LabWorkResponse response = labWorkService.createLabWork(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<LabWorkResponse>> getLabWorksByCourse(@PathVariable Long courseId) {
        log.info("REST: Getting labs for course {}", courseId);
        List<LabWorkResponse> labs = labWorkService.getLabWorksByCourse(courseId);
        return ResponseEntity.ok(labs);
    }


    @GetMapping("/{id}")
    public ResponseEntity<LabWorkResponse> getLabWork(@PathVariable Long id) {
        log.info("REST: Getting lab work {}", id);
        LabWorkResponse response = labWorkService.getLabWorkById(id);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLabWork(@PathVariable Long id) {
        log.info("REST: Deleting lab work {}", id);
        labWorkService.deleteLabWork(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/submit")
    public ResponseEntity<LabSubmissionResponse> submitLabWork(@Valid @RequestBody LabSubmissionRequest request) {
        log.info("REST: Submitting lab work {} for student {}", request.getLabWorkId(), request.getStudentId());
        LabSubmissionResponse response = labWorkService.submitLabWork(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PostMapping("/grade")
    public ResponseEntity<LabSubmissionResponse> gradeLabSubmission(@Valid @RequestBody LabGradeRequest request) {
        log.info("REST: Grading lab submission {}", request.getSubmissionId());
        LabSubmissionResponse response = labWorkService.gradeLabSubmission(request);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{labId}/submissions")
    public ResponseEntity<List<LabSubmissionResponse>> getLabSubmissions(@PathVariable Long labId) {
        log.info("REST: Getting submissions for lab {}", labId);
        List<LabSubmissionResponse> submissions = labWorkService.getSubmissionsByLabWork(labId);
        return ResponseEntity.ok(submissions);
    }
}