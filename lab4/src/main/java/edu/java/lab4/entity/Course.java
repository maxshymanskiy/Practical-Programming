package edu.java.lab4.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String courseId;

    @Column(nullable = false)
    private String courseName;

    private int maxTotalLabPoints;
    private int maxTotalExamPoints;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LabWork> labWorks = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exam> exams = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "course_students",
        joinColumns = @JoinColumn(name = "course_id"),
        inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students = new ArrayList<>();

    public Course(String courseId, String courseName, int maxTotalLabPoints, int maxTotalExamPoints) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.maxTotalLabPoints = maxTotalLabPoints;
        this.maxTotalExamPoints = maxTotalExamPoints;
    }

    public void addLabWork(LabWork labWork) {
        labWorks.add(labWork);
        labWork.setCourse(this);
    }

    public void addExam(Exam exam) {
        exams.add(exam);
        exam.setCourse(this);
    }

    public void addStudent(Student student) {
        students.add(student);
        student.getCourses().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(courseId, course.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId);
    }
}
