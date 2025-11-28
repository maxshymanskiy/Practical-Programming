package edu.java.lab4.controller;

import edu.java.lab4.dto.request.ExamCreateDto;
import edu.java.lab4.dto.request.ExamGradeDto;
import edu.java.lab4.dto.request.ExamSubmissionCreateDto;
import edu.java.lab4.dto.request.TaskCreateDto;
import edu.java.lab4.dto.response.ExamDto;
import edu.java.lab4.dto.response.ExamSubmissionDto;
import edu.java.lab4.dto.response.TaskDto;
import edu.java.lab4.service.*;
import jakarta.validation.Valid;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/exams")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
class ExamController {

    private final ExamService examService;

    @PostMapping
    public ResponseEntity<ExamDto> createExam(@Valid @RequestBody ExamCreateDto request) {
        log.info("REST: Creating exam {}", request.getTitle());
        ExamDto response = examService.createExam(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<ExamDto>> getExamsByCourse(
            @PathVariable Long courseId) {
        log.info("REST: Getting exams for course {}", courseId);
        List<ExamDto> exams = examService.getExamsByCourse(courseId);
        return ResponseEntity.ok(exams);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ExamDto> getExam(@PathVariable Long id) {
        log.info("REST: Getting exam {}", id);
        ExamDto response = examService.getExamById(id);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public void deleteExam(@PathVariable Long id) {
        log.info("REST: Deleting exam {}", id);
        examService.deleteExam(id);
    }


    @PostMapping("/tasks")
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody TaskCreateDto request) {
        log.info("REST: Creating task for exam {}", request.getExamId());
        TaskDto response = examService.createTask(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/{examId}/tasks")
    @PreAuthorize("hasRole('STUDENT') or hasRole('ADMIN')")
    public ResponseEntity<List<TaskDto>> getExamTasks(@PathVariable Long examId) {
        log.info("REST: Getting tasks for exam {}", examId);
        List<TaskDto> tasks = examService.getTasksByExam(examId);
        return ResponseEntity.ok(tasks);
    }


    @PostMapping("/submit")
    @PreAuthorize("hasRole('STUDENT') or hasRole('ADMIN')")
    public ResponseEntity<ExamSubmissionDto> submitExam(@Valid @RequestBody ExamSubmissionCreateDto request) {
        log.info("REST: Submitting exam {} for student {}", request.getExamId(), request.getStudentId());
        ExamSubmissionDto response = examService.submitExam(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PostMapping("/grade")
    public ResponseEntity<ExamSubmissionDto> gradeExamSubmission(@Valid @RequestBody ExamGradeDto request) {
        log.info("REST: Grading exam submission {}", request.getSubmissionId());
        ExamSubmissionDto response = examService.gradeExamSubmission(request);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{examId}/submissions")
    public ResponseEntity<List<ExamSubmissionDto>> getExamSubmissions(@PathVariable Long examId) {
        log.info("REST: Getting submissions for exam {}", examId);
        List<ExamSubmissionDto> submissions = examService.getSubmissionsByExam(examId);
        return ResponseEntity.ok(submissions);
    }
}
