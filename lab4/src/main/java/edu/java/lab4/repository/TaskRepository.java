package edu.java.lab4.repository;

import edu.java.lab4.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    boolean existsByExamIdAndVariantNumber(Long examId, Integer variantNumber);

    List<Task> findByExamIdOrderByVariantNumber(Long examId);
}
