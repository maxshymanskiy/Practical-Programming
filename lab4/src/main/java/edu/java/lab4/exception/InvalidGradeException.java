package edu.java.lab4.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidGradeException extends RuntimeException {

    public InvalidGradeException(String message) {
        super(message);
    }
}
