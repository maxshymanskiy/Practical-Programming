package edu.java.lab4.controller;

import edu.java.lab4.dto.request.CourseCreateDto;
import edu.java.lab4.dto.request.CourseUpdateDto;
import edu.java.lab4.dto.request.StudentEnrollDto;
import edu.java.lab4.dto.response.CourseDetailDto;
import edu.java.lab4.dto.response.CourseDto;
import edu.java.lab4.service.CourseService;
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
@RequestMapping("/api/courses")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseDto> createCourse(@Valid @RequestBody CourseCreateDto request) {
        log.info("REST: Creating new course request received");
        CourseDto response = courseService.createCourse(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping
    public ResponseEntity<List<CourseDto>> getAllCourses(@RequestParam(required = false) String academicYear) {
        log.info("REST: Getting all courses{}", academicYear != null ? " for year " + academicYear : "");
        List<CourseDto> courses = academicYear != null
                ? courseService.getCoursesByAcademicYear(academicYear)
                : courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourse(@PathVariable Long id) {
        log.info("REST: Getting course {}", id);
        CourseDto response = courseService.getCourseById(id);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}/details")
    @PreAuthorize("hasRole('STUDENT') or hasRole('ADMIN')")
    public ResponseEntity<CourseDetailDto> getCourseDetails(@PathVariable Long id) {
        log.info("REST: Getting course details {}", id);
        CourseDetailDto response = courseService.getCourseDetails(id);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CourseDto> updateCourse(@PathVariable Long id, @Valid @RequestBody CourseUpdateDto request) {
        log.info("REST: Updating course {}", id);
        CourseDto response = courseService.updateCourse(id, request);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        log.info("REST: Deleting course {}", id);
        courseService.deleteCourse(id);
    }


    @PostMapping("/enroll")
    public void enrollStudent(@Valid @RequestBody StudentEnrollDto request) {
        log.info("REST: Enrolling student {} in course {}", request.getStudentId(), request.getCourseId());
        courseService.enrollStudent(request.getCourseId(), request.getStudentId());
    }


    @PostMapping("/unenroll")
    public void unenrollStudent(@Valid @RequestBody StudentEnrollDto request) {
        log.info("REST: Unenrolling student {} from course {}", request.getStudentId(), request.getCourseId());
        courseService.unenrollStudent(request.getCourseId(), request.getStudentId());
    }
}