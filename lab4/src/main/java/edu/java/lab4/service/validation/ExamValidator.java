package edu.java.lab4.service.validation;

import edu.java.lab4.entity.Exam;
import edu.java.lab4.entity.Student;
import edu.java.lab4.exception.DuplicateEntityException;
import edu.java.lab4.exception.InvalidSubmissionException;
import edu.java.lab4.repository.ExamSubmissionRepository;
import edu.java.lab4.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExamValidator {

    private final TaskRepository taskRepository;
    private final ExamSubmissionRepository examSubmissionRepository;

    public void validateTaskUniqueness(Long examId, Integer variantNumber) {
        if (taskRepository.existsByExamIdAndVariantNumber(examId, variantNumber)) {
            throw new DuplicateEntityException(
                    String.format("Task variant %d already exists for this exam", variantNumber)
            );
        }
    }

    public void validateExamSubmission(Student student, Exam exam) {
        if (!exam.getCourse().getStudents().contains(student)) {
            throw InvalidSubmissionException.notEnrolled();
        }

        if (examSubmissionRepository.existsByStudentIdAndExamId(student.getId(), exam.getId())) {
            throw InvalidSubmissionException.alreadySubmitted("exam");
        }
    }
}