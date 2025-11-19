package edu.java.lab4.service.impl;

import edu.java.lab4.dto.request.CourseCreateDto;
import edu.java.lab4.dto.request.CourseUpdateDto;
import edu.java.lab4.dto.response.CourseDetailDto;
import edu.java.lab4.dto.response.CourseDto;
import edu.java.lab4.entity.Course;
import edu.java.lab4.entity.Student;
import edu.java.lab4.exception.DuplicateEntityException;
import edu.java.lab4.exception.EntityNotFoundException;
import edu.java.lab4.mapper.CourseMapper;
import edu.java.lab4.repository.CourseRepository;
import edu.java.lab4.repository.StudentRepository;
import edu.java.lab4.service.CourseService;
import edu.java.lab4.service.validation.CourseValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static edu.java.lab4.constant.GradingConstants.CACHE_COURSES;
import static edu.java.lab4.constant.GradingConstants.CACHE_JOURNAL;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final CourseMapper courseMapper;
    private final CourseValidator courseValidator;

    @Override
    @Transactional
    @CacheEvict(value = CACHE_COURSES, allEntries = true)
    public CourseDto createCourse(CourseCreateDto request) {
        log.info("Creating course: {}", request.getName());

        courseValidator.validateCourseUniqueness(
                request.getName(),
                request.getAcademicYear()
        );
        courseValidator.validateGradingFormula(
                request.getLabWeight(),
                request.getLabCount(),
                request.getExamWeight()
        );

        var course = courseMapper.toEntity(request);

        courseRepository.save(course);

        log.info("Course created with ID: {}", course.getId());
        return courseMapper.toResponse(course);
    }

    @Override
    @Transactional
    @CacheEvict(value = {CACHE_COURSES, CACHE_JOURNAL}, allEntries = true)
    public CourseDto updateCourse(Long id, CourseUpdateDto request) {
        log.info("Updating course ID: {}", id);

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course", id));

        if (request.getName() != null) {
            courseValidator.validateCourseNameOnUpdate(
                    request.getName(),
                    course.getName(),
                    course.getAcademicYear()
            );
            course.setName(request.getName());
        }

        if (request.getDescription() != null) {
            course.setDescription(request.getDescription());
        }

        if (request.getAcademicYear() != null) {
            course.setAcademicYear(request.getAcademicYear());
        }

        if (request.getLabWeight() != null || request.getLabCount() != null || request.getExamWeight() != null) {

            Integer labWeight = request.getLabWeight() != null ?
                    request.getLabWeight() : course.getLabWeight();
            Integer labCount = request.getLabCount() != null ?
                    request.getLabCount() : course.getLabCount();
            Integer examWeight = request.getExamWeight() != null ?
                    request.getExamWeight() : course.getExamWeight();

            courseValidator.validateGradingFormula(labWeight, labCount, examWeight);

            course.setLabWeight(labWeight);
            course.setLabCount(labCount);
            course.setExamWeight(examWeight);
        }

        courseRepository.save(course);

        return courseMapper.toResponse(course);
    }

    @Override
    @Transactional
    @CacheEvict(value = {CACHE_COURSES, CACHE_JOURNAL}, allEntries = true)
    public void deleteCourse(Long id) {
        log.info("Deleting course ID: {}", id);

        if (!courseRepository.existsById(id)) {
            throw new EntityNotFoundException("Course", id);
        }

        courseRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = CACHE_COURSES, key = "#id")
    public CourseDto getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course", id));
        return courseMapper.toResponse(course);
    }

    @Override
    @Transactional(readOnly = true)
    public CourseDetailDto getCourseDetails(Long id) {
        Course course = courseRepository.findByIdWithDetails(id)
                .orElseThrow(() -> new EntityNotFoundException("Course", id));
        return courseMapper.toDetailResponse(course);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = CACHE_COURSES, key = "'all'")
    public List<CourseDto> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseDto> getCoursesByAcademicYear(String academicYear) {
        return courseRepository.findByAcademicYear(academicYear)
                .stream()
                .map(courseMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    @CacheEvict(value = {CACHE_COURSES, CACHE_JOURNAL}, allEntries = true)
    public void enrollStudent(Long courseId, Long studentId) {
        log.info("Enrolling student {} in course {}", studentId, courseId);

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course", courseId));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student", studentId));

        if (course.getStudents().contains(student)) {
            throw new DuplicateEntityException("Student already enrolled in this course");
        }

        course.addStudent(student);
        courseRepository.save(course);
    }

    @Override
    @Transactional
    @CacheEvict(value = {CACHE_COURSES, CACHE_JOURNAL}, allEntries = true)
    public void unenrollStudent(Long courseId, Long studentId) {
        log.info("Unenrolling student {} from course {}", studentId, courseId);

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course", courseId));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student", studentId));

        course.removeStudent(student);
        courseRepository.save(course);
    }
}