package edu.java.lab4.controller;

import edu.java.lab4.dto.request.ExamCreateRequest;
import edu.java.lab4.dto.request.ExamGradeRequest;
import edu.java.lab4.dto.request.ExamSubmissionRequest;
import edu.java.lab4.dto.request.TaskCreateRequest;
import edu.java.lab4.dto.response.ExamResponse;
import edu.java.lab4.dto.response.ExamSubmissionResponse;
import edu.java.lab4.dto.response.TaskResponse;
import edu.java.lab4.service.*;
import jakarta.validation.Valid;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/exams")
@RequiredArgsConstructor
class ExamController {

    private final ExamService examService;

    @PostMapping
    public ResponseEntity<ExamResponse> createExam(@Valid @RequestBody ExamCreateRequest request) {
        log.info("REST: Creating exam {}", request.getTitle());
        ExamResponse response = examService.createExam(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<ExamResponse>> getExamsByCourse(
            @PathVariable Long courseId) {
        log.info("REST: Getting exams for course {}", courseId);
        List<ExamResponse> exams = examService.getExamsByCourse(courseId);
        return ResponseEntity.ok(exams);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ExamResponse> getExam(@PathVariable Long id) {
        log.info("REST: Getting exam {}", id);
        ExamResponse response = examService.getExamById(id);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long id) {
        log.info("REST: Deleting exam {}", id);
        examService.deleteExam(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/tasks")
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskCreateRequest request) {
        log.info("REST: Creating task for exam {}", request.getExamId());
        TaskResponse response = examService.createTask(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/{examId}/tasks")
    public ResponseEntity<List<TaskResponse>> getExamTasks(@PathVariable Long examId) {
        log.info("REST: Getting tasks for exam {}", examId);
        List<TaskResponse> tasks = examService.getTasksByExam(examId);
        return ResponseEntity.ok(tasks);
    }


    @PostMapping("/submit")
    public ResponseEntity<ExamSubmissionResponse> submitExam(@Valid @RequestBody ExamSubmissionRequest request) {
        log.info("REST: Submitting exam {} for student {}", request.getExamId(), request.getStudentId());
        ExamSubmissionResponse response = examService.submitExam(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PostMapping("/grade")
    public ResponseEntity<ExamSubmissionResponse> gradeExamSubmission(@Valid @RequestBody ExamGradeRequest request) {
        log.info("REST: Grading exam submission {}", request.getSubmissionId());
        ExamSubmissionResponse response = examService.gradeExamSubmission(request);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{examId}/submissions")
    public ResponseEntity<List<ExamSubmissionResponse>> getExamSubmissions(@PathVariable Long examId) {
        log.info("REST: Getting submissions for exam {}", examId);
        List<ExamSubmissionResponse> submissions = examService.getSubmissionsByExam(examId);
        return ResponseEntity.ok(submissions);
    }
}
