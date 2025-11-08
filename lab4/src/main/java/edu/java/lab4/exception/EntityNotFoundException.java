package edu.java.lab4.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String entityType, Long id) {
        super(String.format("%s with ID %d not found", entityType, id));
    }

    public EntityNotFoundException(String entityType, String identifier) {
        super(String.format("%s with identifier '%s' not found", entityType, identifier));
    }

    public EntityNotFoundException(String entityType, String fieldName, String fieldValue) {
        super(String.format("%s with %s '%s' not found", entityType, fieldName, fieldValue));
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
