package edu.java.lab4.service.impl;

import edu.java.lab4.dto.request.StudentCreateDto;
import edu.java.lab4.dto.response.StudentDetailResponse;
import edu.java.lab4.dto.response.StudentDto;
import edu.java.lab4.entity.Student;
import edu.java.lab4.exception.EntityNotFoundException;
import edu.java.lab4.mapper.StudentMapper;
import edu.java.lab4.repository.StudentRepository;
import edu.java.lab4.service.StudentService;
import edu.java.lab4.service.validation.StudentValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static edu.java.lab4.constant.GradingConstants.CACHE_STUDENTS;


@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final StudentValidator studentValidator;

    @Override
    @Transactional
    @CacheEvict(value = CACHE_STUDENTS, allEntries = true)
    public StudentDto createStudent(StudentCreateDto request) {
        log.info("Creating student: {}", request.getEmail());

        studentValidator.validateStudentUniqueness(request.getEmail(), request.getStudentNumber());

        Student student = studentMapper.toEntity(request);

        studentRepository.save(student);

        log.info("Student created with ID: {}", student.getId());
        return studentMapper.toResponse(student);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = CACHE_STUDENTS, key = "#id")
    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student", id));
        return studentMapper.toResponse(student);
    }

    @Override
    @Transactional(readOnly = true)
    public StudentDetailResponse getStudentDetails(Long id) {
        Student student = studentRepository.findByIdWithCourses(id)
                .orElseThrow(() -> new EntityNotFoundException("Student", id));
        return studentMapper.toDetailResponse(student);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = CACHE_STUDENTS, key = "'all'")
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public StudentDto findByEmail(String email) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Student", "email", email));
        return studentMapper.toResponse(student);
    }

    @Override
    @Transactional(readOnly = true)
    public StudentDto findByStudentNumber(String studentNumber) {
        Student student = studentRepository.findByStudentNumber(studentNumber)
                .orElseThrow(() -> new EntityNotFoundException("Student", "studentNumber", studentNumber));
        return studentMapper.toResponse(student);
    }

    @Override
    @Transactional
    @CacheEvict(value = CACHE_STUDENTS, allEntries = true)
    public void deleteStudent(Long id) {
        log.info("Deleting student ID: {}", id);

        if (!studentRepository.existsById(id)) {
            throw new EntityNotFoundException("Student", id);
        }

        studentRepository.deleteById(id);
    }
}