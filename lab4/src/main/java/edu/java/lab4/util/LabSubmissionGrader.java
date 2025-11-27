package edu.java.lab4.util;

import edu.java.lab4.entity.LabSubmission;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class LabSubmissionGrader {

    public void calculateFinalGrade(LabSubmission submission) {
        if (submission.getRawGrade() == null || submission.getLabWork() == null) {
            return;
        }

        double multiplier = GradingCalculator.calculatePenaltyMultiplier(
                submission.getLabWork(),
                submission.getSubmittedAt()
        );

        submission.setPenaltyApplied(1.0 - multiplier);
        submission.setFinalGrade(submission.getRawGrade() * multiplier);
        submission.setGradedAt(LocalDateTime.now());
    }
}

