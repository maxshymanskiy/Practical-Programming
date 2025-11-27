package edu.java.lab4.repository;

import edu.java.lab4.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByEmail(String email);

    boolean existsByStudentNumber(String studentNumber);

    Optional<Student> findByEmail(String email);

    Optional<Student> findByStudentNumber(String studentNumber);

    @Query("SELECT s FROM Student s " +
            "LEFT JOIN s.courses " +
            "WHERE s.id = :id")
    Optional<Student> findByIdWithCourses(@Param("id") Long id);
}