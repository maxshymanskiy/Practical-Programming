package edu.java.lab4.service.impl;

import edu.java.lab4.dto.request.LabGradeRequest;
import edu.java.lab4.dto.request.LabSubmissionRequest;
import edu.java.lab4.dto.request.LabWorkCreateRequest;
import edu.java.lab4.dto.response.LabSubmissionResponse;
import edu.java.lab4.dto.response.LabWorkResponse;
import edu.java.lab4.entity.*;
import edu.java.lab4.exception.EntityNotFoundException;
import edu.java.lab4.mapper.LabWorkMapper;
import edu.java.lab4.repository.*;
import edu.java.lab4.service.LabWorkService;
import edu.java.lab4.service.validation.LabWorkValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static edu.java.lab4.constant.GradingConstants.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class LabWorkServiceImpl implements LabWorkService {

    private final LabWorkRepository labWorkRepository;
    private final LabSubmissionRepository labSubmissionRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final LabWorkMapper labWorkMapper;
    private final LabWorkValidator labWorkValidator;

    @Override
    @Transactional
    public LabWorkResponse createLabWork(LabWorkCreateRequest request) {
        log.info("Creating lab work: {} for course {}", request.getTitle(), request.getCourseId());

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new EntityNotFoundException("Course", request.getCourseId()));

        labWorkValidator.validateLabWorkUniqueness(
                request.getCourseId(),
                request.getLabNumber()
        );

        if (request.getLatePenaltyPerDay() == null) {
            request.setLatePenaltyPerDay(LATE_SUBMISSION_PENALTY_PERCENT);
        }
        if (request.getMaxLateDays() == null) {
            request.setMaxLateDays(MAX_LATE_DAYS);
        }

        LabWork labWork = labWorkMapper.toEntity(request, course);
        labWork = labWorkRepository.save(labWork);

        log.info("Lab work created with ID: {}", labWork.getId());
        return labWorkMapper.toResponse(labWork);
    }

    @Override
    @Transactional(readOnly = true)
    public LabWorkResponse getLabWorkById(Long id) {
        LabWork labWork = labWorkRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("LabWork", id));
        return labWorkMapper.toResponse(labWork);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LabWorkResponse> getLabWorksByCourse(Long courseId) {
        return labWorkRepository.findByCourseIdOrderByLabNumber(courseId)
                .stream()
                .map(labWorkMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public void deleteLabWork(Long id) {
        log.info("Deleting lab work ID: {}", id);

        if (!labWorkRepository.existsById(id)) {
            throw new EntityNotFoundException("LabWork", id);
        }

        labWorkRepository.deleteById(id);
    }

    @Override
    @Transactional
    public LabSubmissionResponse submitLabWork(LabSubmissionRequest request) {
        log.info("Student {} submitting lab work {}", request.getStudentId(), request.getLabWorkId());

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException("Student", request.getStudentId()));

        LabWork labWork = labWorkRepository.findById(request.getLabWorkId())
                .orElseThrow(() -> new EntityNotFoundException("LabWork", request.getLabWorkId()));

        labWorkValidator.validateLabSubmission(student, labWork);

        LabSubmission submission = labWorkMapper.toSubmissionEntity(request, student, labWork);

        submission = labSubmissionRepository.save(submission);

        log.info("Lab submission created with ID: {}", submission.getId());
        return labWorkMapper.toSubmissionResponse(submission);
    }

    @Override
    @Transactional
    @CacheEvict(value = CACHE_JOURNAL, allEntries = true)
    public LabSubmissionResponse gradeLabSubmission(LabGradeRequest request) {
        log.info("Grading lab submission ID: {}", request.getSubmissionId());

        LabSubmission submission = labSubmissionRepository.findById(request.getSubmissionId())
                .orElseThrow(() -> new EntityNotFoundException("LabSubmission", request.getSubmissionId()));

        submission.setRawGrade(request.getGrade());
        submission.setGraderNotes(request.getGraderNotes());

        submission.calculateFinalGrade();
        submission = labSubmissionRepository.save(submission);

        log.info("Lab submission graded: raw={}, final={}, penalty={}%",
                submission.getRawGrade(),
                submission.getFinalGrade(),
                submission.getPenaltyApplied() * 100);

        return labWorkMapper.toSubmissionResponse(submission);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LabSubmissionResponse> getSubmissionsByLabWork(Long labWorkId) {
        return labSubmissionRepository.findByLabWorkIdOrderBySubmittedAt(labWorkId)
                .stream()
                .map(labWorkMapper::toSubmissionResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<LabSubmissionResponse> getSubmissionsByStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student", studentId));

        return student.getLabSubmissions()
                .stream()
                .map(labWorkMapper::toSubmissionResponse)
                .toList();
    }
}