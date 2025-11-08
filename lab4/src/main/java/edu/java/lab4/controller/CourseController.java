package edu.java.lab4.controller;

import edu.java.lab4.dto.request.CourseCreateRequest;
import edu.java.lab4.dto.request.CourseUpdateRequest;
import edu.java.lab4.dto.request.StudentEnrollRequest;
import edu.java.lab4.dto.response.CourseDetailResponse;
import edu.java.lab4.dto.response.CourseResponse;
import edu.java.lab4.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(@Valid @RequestBody CourseCreateRequest request) {
        log.info("REST: Creating new course request received");
        CourseResponse response = courseService.createCourse(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAllCourses(@RequestParam(required = false) String academicYear) {
        log.info("REST: Getting all courses{}", academicYear != null ? " for year " + academicYear : "");
        List<CourseResponse> courses = academicYear != null
                ? courseService.getCoursesByAcademicYear(academicYear)
                : courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getCourse(@PathVariable Long id) {
        log.info("REST: Getting course {}", id);
        CourseResponse response = courseService.getCourseById(id);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}/details")
    public ResponseEntity<CourseDetailResponse> getCourseDetails(@PathVariable Long id) {
        log.info("REST: Getting course details {}", id);
        CourseDetailResponse response = courseService.getCourseDetails(id);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse> updateCourse(@PathVariable Long id, @Valid @RequestBody CourseUpdateRequest request) {
        log.info("REST: Updating course {}", id);
        CourseResponse response = courseService.updateCourse(id, request);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        log.info("REST: Deleting course {}", id);
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/enroll")
    public ResponseEntity<Void> enrollStudent(@Valid @RequestBody StudentEnrollRequest request) {
        log.info("REST: Enrolling student {} in course {}", request.getStudentId(), request.getCourseId());
        courseService.enrollStudent(request.getCourseId(), request.getStudentId());
        return ResponseEntity.ok().build();
    }


    @PostMapping("/unenroll")
    public ResponseEntity<Void> unenrollStudent(@Valid @RequestBody StudentEnrollRequest request) {
        log.info("REST: Unenrolling student {} from course {}", request.getStudentId(), request.getCourseId());
        courseService.unenrollStudent(request.getCourseId(), request.getStudentId());
        return ResponseEntity.ok().build();
    }
}