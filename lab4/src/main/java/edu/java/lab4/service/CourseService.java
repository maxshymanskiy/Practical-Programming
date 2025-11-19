
package edu.java.lab4.service;

import edu.java.lab4.dto.request.CourseCreateDto;
import edu.java.lab4.dto.request.CourseUpdateDto;
import edu.java.lab4.dto.response.CourseDetailDto;
import edu.java.lab4.dto.response.CourseDto;
import java.util.List;


public interface CourseService {

    CourseDto createCourse(CourseCreateDto request);

    CourseDto updateCourse(Long id, CourseUpdateDto request);

    void deleteCourse(Long id);

    CourseDto getCourseById(Long id);

    CourseDetailDto getCourseDetails(Long id);

    List<CourseDto> getAllCourses();

    List<CourseDto> getCoursesByAcademicYear(String academicYear);

    void enrollStudent(Long courseId, Long studentId);

    void unenrollStudent(Long courseId, Long studentId);
}