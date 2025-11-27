package edu.java.lab4.util;

import edu.java.lab4.entity.Course;
import edu.java.lab4.entity.Student;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CourseStudentManager {

    public void addStudent(Course course, Student student) {
        course.getStudents().add(student);
        student.getCourses().add(course);
    }

    public void removeStudent(Course course, Student student) {
        course.getStudents().remove(student);
        student.getCourses().remove(course);
    }
}

