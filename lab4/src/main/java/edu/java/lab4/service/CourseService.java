
package edu.java.lab4.service;

import edu.java.lab4.dto.request.CourseCreateRequest;
import edu.java.lab4.dto.request.CourseUpdateRequest;
import edu.java.lab4.dto.response.CourseDetailResponse;
import edu.java.lab4.dto.response.CourseResponse;
import java.util.List;


public interface CourseService {

    CourseResponse createCourse(CourseCreateRequest request);

    CourseResponse updateCourse(Long id, CourseUpdateRequest request);

    void deleteCourse(Long id);

    CourseResponse getCourseById(Long id);

    CourseDetailResponse getCourseDetails(Long id);

    List<CourseResponse> getAllCourses();

    List<CourseResponse> getCoursesByAcademicYear(String academicYear);

    void enrollStudent(Long courseId, Long studentId);

    void unenrollStudent(Long courseId, Long studentId);
}