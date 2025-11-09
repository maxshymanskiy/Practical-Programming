package edu.java.lab4.mapper;

import edu.java.lab4.dto.request.*;
import edu.java.lab4.dto.response.*;
import edu.java.lab4.entity.*;
import org.springframework.stereotype.Component;

@Component
public class LabWorkMapper {

    public LabWork toEntity(LabWorkCreateRequest request, Course course) {
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

    public LabWorkResponse toResponse(LabWork labWork) {
        return LabWorkResponse.builder()
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

    public LabSubmissionResponse toSubmissionResponse(LabSubmission submission) {
        return LabSubmissionResponse.builder()
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