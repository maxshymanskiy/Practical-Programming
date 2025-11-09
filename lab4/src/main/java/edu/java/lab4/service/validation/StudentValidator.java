package edu.java.lab4.service.validation;

import edu.java.lab4.exception.DuplicateEntityException;
import edu.java.lab4.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentValidator {

    private final StudentRepository studentRepository;

    public void validateStudentUniqueness(String email, String studentNumber) {
        if (studentRepository.existsByEmail(email)) {
            throw new DuplicateEntityException("Student", "email", email);
        }

        if (studentRepository.existsByStudentNumber(studentNumber)) {
            throw new DuplicateEntityException("Student", "studentNumber", studentNumber);
        }
    }
}