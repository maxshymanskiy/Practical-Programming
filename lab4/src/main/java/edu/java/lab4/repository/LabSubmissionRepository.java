package edu.java.lab4.repository;

import edu.java.lab4.entity.LabSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LabSubmissionRepository extends JpaRepository<LabSubmission, Long> {

    boolean existsByStudentIdAndLabWorkId(Long studentId, Long labWorkId);

    Optional<LabSubmission> findByStudentIdAndLabWorkId(Long studentId, Long labWorkId);

    List<LabSubmission> findByLabWorkIdOrderBySubmittedAt(Long labWorkId);

    @Query("SELECT ls FROM LabSubmission ls " +
            "WHERE ls.student.id = :studentId " +
            "AND ls.labWork.course.id = :courseId")
    List<LabSubmission> findByStudentIdAndCourseId(@Param("studentId") Long studentId, @Param("courseId") Long courseId);

    List<LabSubmission> findByLabWorkIdAndGradedAtIsNull(Long labWorkId);
}