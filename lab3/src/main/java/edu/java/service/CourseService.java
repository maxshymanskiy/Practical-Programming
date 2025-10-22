package edu.java.service;

import edu.java.exception.DuplicateCourseException;
import edu.java.exception.EntityNotFoundException;
import edu.java.model.Course;
import edu.java.model.Exam;
import edu.java.model.LabWork;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CourseService {
    private Map<String, Course> courses;

    public CourseService() {
        this.courses = new HashMap<>();
    }

    public Course createCourse(String courseId, String name) throws DuplicateCourseException {
        if (courses.containsKey(courseId)) {
            throw new DuplicateCourseException("Course with ID " + courseId + " already exists");
        }

        Course course = new Course(courseId, name);
        courses.put(courseId, course);
        return course;
    }

    public LabWork addLabWork(Course course, String labId, String title, LocalDate deadline) {
        LabWork labWork = new LabWork(labId, title, deadline);
        course.addLabWork(labWork);
        return labWork;
    }

    public Exam addExam(Course course, String examId, String title, LocalDate examDate) {
        Exam exam = new Exam(examId, title, examDate);
        course.addExam(exam);
        return exam;
    }

    public Course findCourseById(String courseId) throws EntityNotFoundException {
        return Optional.ofNullable(courses.get(courseId))
                .orElseThrow(() -> new EntityNotFoundException("Course not found: " + courseId));
    }
}