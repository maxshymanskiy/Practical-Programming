package edu.java.lab4.service.validation;

import edu.java.lab4.entity.LabWork;
import edu.java.lab4.entity.Student;
import edu.java.lab4.exception.DuplicateEntityException;
import edu.java.lab4.exception.InvalidSubmissionException;
import edu.java.lab4.repository.LabSubmissionRepository;
import edu.java.lab4.repository.LabWorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class LabWorkValidator {

    private final LabWorkRepository labWorkRepository;
    private final LabSubmissionRepository labSubmissionRepository;

    public void validateLabWorkUniqueness(Long courseId, Integer labNumber) {
        if (labWorkRepository.existsByCourseIdAndLabNumber(courseId, labNumber)) {
            throw new DuplicateEntityException(
                    String.format("Lab %d already exists for this course", labNumber)
            );
        }
    }

    public void validateLabSubmission(Student student, LabWork labWork) {
        // Перевірка чи студент записаний на курс
        if (!labWork.getCourse().getStudents().contains(student)) {
            throw InvalidSubmissionException.notEnrolled();
        }

        // Перевірка чи вже здавав
        if (labSubmissionRepository.existsByStudentIdAndLabWorkId(
                student.getId(), labWork.getId())) {
            throw InvalidSubmissionException.alreadySubmitted("lab work");
        }

        // Перевірка дедлайну
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(labWork.getDeadline())) {
            if (!labWork.getAllowsLateSubmission()) {
                throw InvalidSubmissionException.deadlinePassed(false);
            }

            // Перевірка чи не занадто пізно
            if (labWork.getMaxLateDays() != null) {
                long daysLate = java.time.Duration.between(
                        labWork.getDeadline(), now).toDays();

                if (daysLate > labWork.getMaxLateDays()) {
                    throw InvalidSubmissionException.deadlinePassed(true);
                }
            }
        }
    }
}