package edu.java.lab4.service.impl;

import edu.java.lab4.dto.response.*;
import edu.java.lab4.entity.*;
import edu.java.lab4.exception.EntityNotFoundException;
import edu.java.lab4.mapper.JournalMapper;
import edu.java.lab4.repository.*;
import edu.java.lab4.service.GradeService;
import edu.java.lab4.util.GradingCalculator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static edu.java.lab4.constant.GradingConstants.CACHE_JOURNAL;

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
    public JournalDto calculateCourseJournal(Long courseId) {
        log.info("Calculating journal for course ID: {}", courseId);

        Course course = courseRepository.findByIdWithDetails(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course", courseId));

        List<StudentGradeDto> studentGrades = course.getStudents()
                .stream()
                .map(student -> calculateStudentGrade(course, student))
                .sorted(Comparator.comparing(StudentGradeDto::getStudentName))
                .toList();

        return journalMapper.toJournalResponse(course, studentGrades);
    }

    @Override
    @Transactional(readOnly = true)
    public Double calculateStudentTotalGrade(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course", courseId));
        
        if (!isStudentEnrolled(course, studentId)) {
            log.info("Student {} is not enrolled in course {}. Returning zero grade.", studentId, courseId);
            return 0.0;
        }
        
        return calculateTotalGrade(course, studentId);
    }

    
    private boolean isStudentEnrolled(Course course, Long studentId) {
        return course.getStudents()
                .stream()
                .anyMatch(student -> student.getId().equals(studentId));
    }
    
    private Double calculateTotalGrade(Course course, Long studentId) {
        Double labTotal = calculateLabTotal(course, studentId);
        Double examGrade = getExamGrade(course, studentId);
        return labTotal + (examGrade != null ? examGrade : 0.0);
    }

    private StudentGradeDto calculateStudentGrade(Course course, Student student) {
        List<Double> labGrades = collectLabGrades(course, student);
        double labTotal = labGrades
                .stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        Double examGrade = getExamGrade(course, student.getId());
        Integer maxGrade = GradingCalculator.calculateMaxGrade(course);

        return journalMapper.toStudentGradeResponse(student, labGrades, labTotal, examGrade, maxGrade);
    }

    private List<Double> collectLabGrades(Course course, Student student) {
        return course.getLabWorks().stream()
                .sorted(Comparator.comparing(LabWork::getLabNumber))
                .map(labWork -> labSubmissionRepository.findByStudentIdAndLabWorkId(student.getId(), labWork.getId())
                        .map(LabSubmission::getFinalGrade)
                        .orElse(0.0))
                .toList();
    }

    private Double calculateLabTotal(Course course, Long studentId) {
        return labSubmissionRepository.findByStudentIdAndCourseId(studentId, course.getId())
                .stream()
                .map(LabSubmission::getFinalGrade)
                .filter(Objects::nonNull)
                .reduce(0.0, Double::sum);
    }

    private Double getExamGrade(Course course, Long studentId) {
        return course.getExams()
                .stream()
                .findFirst()
                .flatMap(exam -> examSubmissionRepository.findByStudentIdAndExamId(studentId, exam.getId())
                        .map(ExamSubmission::getGrade))
                .orElse(null);
    }
}