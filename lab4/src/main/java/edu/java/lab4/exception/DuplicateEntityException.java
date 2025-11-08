package edu.java.lab4.exception;

public class DuplicateEntityException extends RuntimeException {

    public DuplicateEntityException(String entityType, String field, String value) {
        super(String.format("%s with %s '%s' already exists", entityType, field, value));
    }

    public DuplicateEntityException(String message) {
        super(message);
    }
}
