package edu.java.lab4.controller;

import edu.java.lab4.dto.response.JournalResponse;
import edu.java.lab4.service.GradeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/journal")
@RequiredArgsConstructor
public class JournalController {

    private final GradeService gradeService;

    @GetMapping("/course/{courseId}")
    public ResponseEntity<JournalResponse> getCourseJournal(@PathVariable Long courseId) {
        log.info("REST: Getting journal for course {}", courseId);
        JournalResponse journal = gradeService.calculateCourseJournal(courseId);
        return ResponseEntity.ok(journal);
    }


    @GetMapping("/course/{courseId}/student/{studentId}")
    public ResponseEntity<Double> getStudentGrade(@PathVariable Long courseId, @PathVariable Long studentId) {
        log.info("REST: Getting grade for student {} in course {}", studentId, courseId);
        Double grade = gradeService.calculateStudentTotalGrade(courseId, studentId);
        return ResponseEntity.ok(grade);
    }
}