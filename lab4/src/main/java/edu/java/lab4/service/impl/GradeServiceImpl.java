package edu.java.lab4.service.impl;

import edu.java.lab4.dto.response.*;
        import edu.java.lab4.entity.*;
        import edu.java.lab4.exception.EntityNotFoundException;
import edu.java.lab4.mapper.JournalMapper;
import edu.java.lab4.repository.*;
        import edu.java.lab4.service.GradeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static edu.java.lab4.constant.GradingConstants.*;

/**
 * Calculates student grades and generates journal reports.
 * USES CACHING: Journal calculation is expensive (read-heavy).
 * Cache is invalidated when grades are updated.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {

    private final CourseRepository courseRepository;
    private final LabSubmissionRepository labSubmissionRepository;
    private final ExamSubmissionRepository examSubmissionRepository;
    private final JournalMapper journalMapper;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = CACHE_JOURNAL, key = "#courseId")
    public JournalResponse calculateCourseJournal(Long courseId) {
        log.info("Calculating journal for course ID: {}", courseId);

        Course course = courseRepository.findByIdWithDetails(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course", courseId));

        List<StudentGradeResponse> studentGrades = course.getStudents()
                .stream()
                .map(student -> calculateStudentGrade(course, student))
                .sorted(Comparator.comparing(StudentGradeResponse::getStudentName))
                .toList();

        return journalMapper.toJournalResponse(course, studentGrades);
    }

    @Override
    @Transactional(readOnly = true)
    public Double calculateStudentTotalGrade(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course", courseId));

        Double labTotal = calculateLabTotal(course, studentId);
        Double examGrade = getExamGrade(course, studentId);

        return labTotal + (examGrade != null ? examGrade : 0.0);
    }

    private StudentGradeResponse calculateStudentGrade(Course course, Student student) {
        List<Double> labGrades = new ArrayList<>();
        double labTotal = 0.0;

        for (int i = 1; i <= course.getLabCount(); i++) {
            final int labNumber = i;

            Optional<LabWork> labWork = course.getLabWorks()
                    .stream()
                    .filter(l -> l.getLabNumber().equals(labNumber))
                    .findFirst();

            if (labWork.isPresent()) {
                Optional<LabSubmission> submission = labSubmissionRepository.findByStudentIdAndLabWorkId(
                                student.getId(),
                                labWork.get().getId());

                double grade = submission
                        .map(LabSubmission::getFinalGrade)
                        .orElse(0.0);

                labGrades.add(grade);
                labTotal += grade;
            } else {
                labGrades.add(0.0);
            }
        }

        Double examGrade = getExamGrade(course, student.getId());
        Integer maxGrade = course.calculateMaxGrade();

        return journalMapper.toStudentGradeResponse(student, labGrades, labTotal, examGrade, maxGrade);
    }

    private Double calculateLabTotal(Course course, Long studentId) {
        return labSubmissionRepository.findByStudentIdAndCourseId(studentId, course.getId())
                .stream()
                .map(LabSubmission::getFinalGrade)
                .filter(Objects::nonNull)
                .reduce(0.0, Double::sum);
    }

    private Double getExamGrade(Course course, Long studentId) {
        return course.getExams().stream()
                .findFirst()
                .flatMap(exam -> examSubmissionRepository.findByStudentIdAndExamId(studentId, exam.getId())
                        .map(ExamSubmission::getGrade))
                .orElse(null);
    }
}