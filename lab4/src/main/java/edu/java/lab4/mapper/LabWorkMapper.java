package edu.java.lab4.mapper;

import edu.java.lab4.dto.request.*;
import edu.java.lab4.dto.response.*;
import edu.java.lab4.entity.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LabWorkMapper {

    public LabWork toEntity(LabWorkCreateDto request, Course course) {
        return LabWork.builder()
                .labNumber(request.getLabNumber())
                .title(request.getTitle())
                .description(request.getDescription())
                .deadline(request.getDeadline())
                .allowsLateSubmission(request.getAllowsLateSubmission())
                .latePenaltyPerDay(request.getLatePenaltyPerDay())
                .maxLateDays(request.getMaxLateDays())
                .course(course)
                .build();
    }

    public LabWorkDto toResponse(LabWork labWork) {
        return LabWorkDto.builder()
                .id(labWork.getId())
                .labNumber(labWork.getLabNumber())
                .title(labWork.getTitle())
                .description(labWork.getDescription())
                .deadline(labWork.getDeadline())
                .allowsLateSubmission(labWork.getAllowsLateSubmission())
                .latePenaltyPerDay(labWork.getLatePenaltyPerDay())
                .maxLateDays(labWork.getMaxLateDays())
                .courseId(labWork.getCourse().getId())
                .courseName(labWork.getCourse().getName())
                .submissionsCount(labWork.getSubmissions().size())
                .createdAt(labWork.getCreatedAt())
                .build();
    }

    public LabSubmission toSubmissionEntity(LabSubmissionCreateDto request, Student student, LabWork labWork) {
        return LabSubmission.builder()
                .student(student)
                .labWork(labWork)
                .submissionUrl(request.getSubmissionUrl())
                .notes(request.getNotes())
                .submittedAt(LocalDateTime.now())
                .build();
    }

    public LabSubmissionDto toSubmissionResponse(LabSubmission submission) {
        return LabSubmissionDto.builder()
                .id(submission.getId())
                .studentId(submission.getStudent().getId())
                .studentName(submission.getStudent().getFullName())
                .labWorkId(submission.getLabWork().getId())
                .labTitle(submission.getLabWork().getTitle())
                .submissionUrl(submission.getSubmissionUrl())
                .notes(submission.getNotes())
                .submittedAt(submission.getSubmittedAt())
                .isLate(submission.getIsLate())
                .rawGrade(submission.getRawGrade())
                .finalGrade(submission.getFinalGrade())
                .penaltyApplied(submission.getPenaltyApplied())
                .gradedAt(submission.getGradedAt())
                .graderNotes(submission.getGraderNotes())
                .build();
    }
}