package edu.java.lab4.repository;

import edu.java.lab4.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ExamRepository extends JpaRepository<Exam, Long> {

    List<Exam> findByCourseIdOrderByScheduledDate(Long courseId);

    @Query("SELECT e FROM Exam e " +
            "LEFT JOIN e.tasks " +
            "WHERE e.id = :id")
    Optional<Exam> findByIdWithTasks(@Param("id") Long id);
}
