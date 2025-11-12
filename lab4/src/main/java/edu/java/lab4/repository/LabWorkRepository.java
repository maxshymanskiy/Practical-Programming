package edu.java.lab4.repository;

import edu.java.lab4.entity.LabWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LabWorkRepository extends JpaRepository<LabWork, Long> {

    boolean existsByCourseIdAndLabNumber(Long courseId, Integer labNumber);

    List<LabWork> findByCourseIdOrderByLabNumber(Long courseId);
}
