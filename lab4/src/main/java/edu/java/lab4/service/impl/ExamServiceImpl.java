package edu.java.lab4.service.impl;

import edu.java.lab4.dto.request.*;
import edu.java.lab4.dto.response.*;
import edu.java.lab4.entity.*;
import edu.java.lab4.exception.EntityNotFoundException;
import edu.java.lab4.mapper.ExamMapper;
import edu.java.lab4.repository.*;
import edu.java.lab4.service.ExamService;
import edu.java.lab4.service.validation.ExamValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

import static edu.java.lab4.constant.GradingConstants.CACHE_JOURNAL;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;
    private final ExamSubmissionRepository examSubmissionRepository;
    private final TaskRepository taskRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final ExamMapper examMapper;
    private final ExamValidator examValidator;
    private final Random random = new Random();

    @Override
    @Transactional
    public ExamResponse createExam(ExamCreateRequest request) {
        log.info("Creating exam: {} for course {}", request.getTitle(), request.getCourseId());

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new EntityNotFoundException("Course", request.getCourseId()));

        Exam exam = examMapper.toEntity(request, course);
        exam = examRepository.save(exam);

        log.info("Exam created with ID: {}", exam.getId());
        return examMapper.toResponse(exam);
    }

    @Override
    @Transactional(readOnly = true)
    public ExamResponse getExamById(Long id) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Exam", id));
        return examMapper.toResponse(exam);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExamResponse> getExamsByCourse(Long courseId) {
        return examRepository.findByCourseIdOrderByScheduledDate(courseId)
                .stream()
                .map(examMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public void deleteExam(Long id) {
        log.info("Deleting exam ID: {}", id);

        if (!examRepository.existsById(id)) {
            throw new EntityNotFoundException("Exam", id);
        }

        examRepository.deleteById(id);
    }

    @Override
    @Transactional
    public TaskResponse createTask(TaskCreateRequest request) {
        log.info("Creating task variant {} for exam {}", request.getVariantNumber(), request.getExamId());

        Exam exam = examRepository.findById(request.getExamId())
                .orElseThrow(() -> new EntityNotFoundException("Exam", request.getExamId()));

        examValidator.validateTaskUniqueness(
                request.getExamId(),
                request.getVariantNumber()
        );

        Task task = examMapper.toTaskEntity(request, exam);

        task = taskRepository.save(task);

        log.info("Task created with ID: {}", task.getId());
        return examMapper.toTaskResponse(task);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponse> getTasksByExam(Long examId) {
        return taskRepository.findByExamIdOrderByVariantNumber(examId)
                .stream()
                .map(examMapper::toTaskResponse)
                .toList();
    }

    @Override
    @Transactional
    public ExamSubmissionResponse submitExam(ExamSubmissionRequest request) {
        log.info("Student {} submitting exam {}", request.getStudentId(), request.getExamId());

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException("Student", request.getStudentId()));

        Exam exam = examRepository.findByIdWithTasks(request.getExamId())
                .orElseThrow(() -> new EntityNotFoundException("Exam", request.getExamId()));

        examValidator.validateExamSubmission(student, exam);

        Task assignedTask = assignTaskToStudent(request, exam);

        ExamSubmission submission = examMapper.toSubmissionEntity(request, student, exam, assignedTask);

        submission = examSubmissionRepository.save(submission);

        log.info("Exam submission created with ID: {}, task variant: {}",
                submission.getId(),
                assignedTask != null ? assignedTask.getVariantNumber() : "N/A");

        return examMapper.toSubmissionResponse(submission);
    }

    @Override
    @Transactional
    @CacheEvict(value = CACHE_JOURNAL, allEntries = true)
    public ExamSubmissionResponse gradeExamSubmission(ExamGradeRequest request) {
        log.info("Grading exam submission ID: {}", request.getSubmissionId());

        ExamSubmission submission = examSubmissionRepository.findById(request.getSubmissionId())
                .orElseThrow(() -> new EntityNotFoundException("ExamSubmission", request.getSubmissionId()));

        submission.setGrade(request.getGrade());
        submission.setGraderNotes(request.getGraderNotes());
        submission.setGradedAt(java.time.LocalDateTime.now());

        submission = examSubmissionRepository.save(submission);

        log.info("Exam submission graded: {}", submission.getGrade());
        return examMapper.toSubmissionResponse(submission);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExamSubmissionResponse> getSubmissionsByExam(Long examId) {
        return examSubmissionRepository.findByExamIdOrderBySubmittedAt(examId)
                .stream()
                .map(examMapper::toSubmissionResponse)
                .toList();
    }


    /**
     * Assigns a task to a student if not specified in the request.
     */
    private Task assignTaskToStudent(ExamSubmissionRequest request, Exam exam) {
        if (request.getAssignedTaskId() != null) {
            return taskRepository.findById(request.getAssignedTaskId())
                    .orElseThrow(() -> new EntityNotFoundException("Task", request.getAssignedTaskId()));
        }

        List<Task> tasks = exam.getTasks();
        if (tasks.isEmpty()) {
            log.warn("No tasks available for exam {}", exam.getId());
            return null;
        }

        return tasks.get(random.nextInt(tasks.size()));
    }
}