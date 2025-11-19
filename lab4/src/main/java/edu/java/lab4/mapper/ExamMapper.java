package edu.java.lab4.mapper;

import edu.java.lab4.dto.request.*;
import edu.java.lab4.dto.response.*;
import edu.java.lab4.entity.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ExamMapper {

    public Exam toEntity(ExamCreateDto request, Course course) {
        return Exam.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .scheduledDate(request.getScheduledDate())
                .durationMinutes(request.getDurationMinutes())
                .course(course)
                .build();
    }

    public Task toTaskEntity(TaskCreateDto request, Exam exam) {
        return Task.builder()
                .exam(exam)
                .variantNumber(request.getVariantNumber())
                .title(request.getTitle())
                .description(request.getDescription())
                .build();
    }

    public ExamSubmission toSubmissionEntity(ExamSubmissionCreateDto request, Student student, Exam exam, Task assignedTask) {
        return ExamSubmission.builder()
                .student(student)
                .exam(exam)
                .assignedTask(assignedTask)
                .submissionUrl(request.getSubmissionUrl())
                .answer(request.getAnswer())
                .submittedAt(LocalDateTime.now())
                .build();
    }

    public ExamDto toResponse(Exam exam) {
        return ExamDto.builder()
                .id(exam.getId())
                .title(exam.getTitle())
                .description(exam.getDescription())
                .scheduledDate(exam.getScheduledDate())
                .durationMinutes(exam.getDurationMinutes())
                .courseId(exam.getCourse().getId())
                .courseName(exam.getCourse().getName())
                .tasksCount(exam.getTasks().size())
                .submissionsCount(exam.getSubmissions().size())
                .createdAt(exam.getCreatedAt())
                .build();
    }

    public TaskDto toTaskResponse(Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .variantNumber(task.getVariantNumber())
                .title(task.getTitle())
                .description(task.getDescription())
                .examId(task.getExam().getId())
                .examTitle(task.getExam().getTitle())
                .createdAt(task.getCreatedAt())
                .build();
    }

    public ExamSubmissionDto toSubmissionResponse(ExamSubmission submission) {
        return ExamSubmissionDto.builder()
                .id(submission.getId())
                .studentId(submission.getStudent().getId())
                .studentName(submission.getStudent().getFullName())
                .examId(submission.getExam().getId())
                .examTitle(submission.getExam().getTitle())
                .assignedTaskId(submission.getAssignedTask() != null ?
                        submission.getAssignedTask().getId() : null)
                .taskVariant(submission.getAssignedTask() != null ?
                        submission.getAssignedTask().getVariantNumber() : null)
                .submissionUrl(submission.getSubmissionUrl())
                .answer(submission.getAnswer())
                .submittedAt(submission.getSubmittedAt())
                .grade(submission.getGrade())
                .gradedAt(submission.getGradedAt())
                .graderNotes(submission.getGraderNotes())
                .build();
    }
}