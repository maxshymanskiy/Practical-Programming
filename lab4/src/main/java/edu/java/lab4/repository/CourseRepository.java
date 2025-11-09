package edu.java.lab4.repository;

import edu.java.lab4.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    boolean existsByNameAndAcademicYear(String name, String academicYear);

    List<Course> findByAcademicYear(String academicYear);

    @Query("SELECT DISTINCT c FROM Course c " +
            "LEFT JOIN FETCH c.students " +
            "LEFT JOIN FETCH c.labWorks " +
            "LEFT JOIN FETCH c.exams " +
            "WHERE c.id = :id")
    Optional<Course> findByIdWithDetails(@Param("id") Long id);
}
