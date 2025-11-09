package edu.java.lab4.mapper;

import edu.java.lab4.dto.request.*;
import edu.java.lab4.dto.response.*;
import edu.java.lab4.entity.*;
import org.springframework.stereotype.Component;

@Component
public class ExamMapper {

    public Exam toEntity(ExamCreateRequest request, Course course) {
        return Exam.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .scheduledDate(request.getScheduledDate())
                .durationMinutes(request.getDurationMinutes())
                .course(course)
                .build();
    }

    public ExamResponse toResponse(Exam exam) {
        return ExamResponse.builder()
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

    public TaskResponse toTaskResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .variantNumber(task.getVariantNumber())
                .title(task.getTitle())
                .description(task.getDescription())
                .examId(task.getExam().getId())
                .examTitle(task.getExam().getTitle())
                .createdAt(task.getCreatedAt())
                .build();
    }

    public ExamSubmissionResponse toSubmissionResponse(ExamSubmission submission) {
        return ExamSubmissionResponse.builder()
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