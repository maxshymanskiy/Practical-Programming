package edu.java.lab4.util;

import edu.java.lab4.entity.Course;
import edu.java.lab4.entity.LabWork;
import lombok.experimental.UtilityClass;

import java.time.Duration;
import java.time.LocalDateTime;

@UtilityClass
public class GradingCalculator {

    public int calculateMaxGrade(Integer labWeight, Integer labCount, Integer examWeight) {
        return (labWeight * labCount) + examWeight;
    }

    public int calculateMaxGrade(Course course) {
        return calculateMaxGrade(course.getLabWeight(), course.getLabCount(), course.getExamWeight());
    }

    public double calculatePenaltyMultiplier(LocalDateTime deadline,
                                             LocalDateTime submissionTime,
                                             Boolean allowsLateSubmission,
                                             Double latePenaltyPerDay,
                                             Integer maxLateDays) {
        if (submissionTime.isBefore(deadline) || !allowsLateSubmission) {
            return 1.0; // No penalty
        }

        long daysLate = Duration.between(deadline, submissionTime).toDays();
        if (maxLateDays != null && daysLate > maxLateDays) {
            return 0.0; // Too late, no points
        }

        double penalty = (latePenaltyPerDay != null ? latePenaltyPerDay : 0.0) * daysLate;
        return Math.max(0.0, (1.0 - penalty));
    }

    public double calculatePenaltyMultiplier(LabWork labWork, LocalDateTime submissionTime) {
        return calculatePenaltyMultiplier(
                labWork.getDeadline(),
                submissionTime,
                labWork.getAllowsLateSubmission(),
                labWork.getLatePenaltyPerDay(),
                labWork.getMaxLateDays()
        );
    }

    public double calculateFinalGrade(Double rawGrade, double penaltyMultiplier) {
        if (rawGrade == null) {
            return 0.0;
        }
        return rawGrade * penaltyMultiplier;
    }
}

