package edu.java.service;

import edu.java.exception.DuplicateCourseException;
import edu.java.exception.EntityNotFoundException;
import edu.java.model.Course;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseService {

    private final Map<String, Course> courses = new HashMap<>();

    public CourseService() {}

    public Course createCourse(String courseId, String courseName, int maxTotalLabPoints, int maxTotalExamPoints) {
        if (courses.containsKey(courseId)) {
            throw new DuplicateCourseException("Course with ID " + courseId + " already exists");
        }

        Course course = new Course(courseId, courseName, maxTotalLabPoints, maxTotalExamPoints);
        courses.put(courseId, course);
        return course;
    }

    public void getCourse(String courseId) {
        Course course = courses.get(courseId);
        if (course == null) {
            throw new EntityNotFoundException("Course with ID " + courseId + " not found");
        }
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courses.values());
    }
}