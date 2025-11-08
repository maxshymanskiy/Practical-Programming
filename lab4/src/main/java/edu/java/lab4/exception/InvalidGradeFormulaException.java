package edu.java.lab4.exception;

public class InvalidGradeFormulaException extends RuntimeException {

    public InvalidGradeFormulaException(String message) {
        super(message);
    }

    public static InvalidGradeFormulaException invalidWeights() {
        return new InvalidGradeFormulaException(
                "Grade formula weights must be positive numbers"
        );
    }

    public static InvalidGradeFormulaException invalidTotal(int total, int max) {
        return new InvalidGradeFormulaException(
                String.format("Total grade (%d) exceeds maximum allowed (%d)", total, max)
        );
    }
}