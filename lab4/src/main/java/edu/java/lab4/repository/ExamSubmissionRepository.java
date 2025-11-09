package edu.java.lab4.repository;

import edu.java.lab4.entity.ExamSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExamSubmissionRepository extends JpaRepository<ExamSubmission, Long> {

    boolean existsByStudentIdAndExamId(Long studentId, Long examId);

    Optional<ExamSubmission> findByStudentIdAndExamId(Long studentId, Long examId);

    List<ExamSubmission> findByExamIdOrderBySubmittedAt(Long examId);

    List<ExamSubmission> findByExamIdAndGradedAtIsNull(Long examId);
}