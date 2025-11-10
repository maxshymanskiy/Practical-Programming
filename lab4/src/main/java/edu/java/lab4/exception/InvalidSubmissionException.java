package edu.java.lab4.exception;

public class InvalidSubmissionException extends RuntimeException {

    public InvalidSubmissionException(String message) {
        super(message);
    }

    public static InvalidSubmissionException alreadySubmitted(String submissionType) {
        return new InvalidSubmissionException(
                String.format("Student has already submitted this %s", submissionType)
        );
    }

    public static InvalidSubmissionException notEnrolled() {
        return new InvalidSubmissionException(
                "Student is not enrolled in this course"
        );
    }

    public static InvalidSubmissionException deadlinePassed(boolean allowsLate) {
        if (allowsLate) {
            return new InvalidSubmissionException("Submission is too late - maximum late days exceeded");
        }
        return new InvalidSubmissionException("Deadline has passed and late submissions are not allowed");
    }
}
