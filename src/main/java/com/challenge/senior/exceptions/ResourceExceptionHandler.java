package com.challenge.senior.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    private final static int BAD_REQUEST = HttpStatus.BAD_REQUEST.value();
    private final static int NOT_FOUND = HttpStatus.NOT_FOUND.value();

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(final ResourceNotFoundException exception,
                                                          final HttpServletRequest request) {
        final StandardError error = new StandardError(
                Instant.now(),
                NOT_FOUND,
                "Resource not found",
                exception.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(NOT_FOUND).body(error);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> illegalArgumentException(final DatabaseException exception,
                                                                  final HttpServletRequest request) {
        final StandardError error = new StandardError(
                Instant.now(),
                BAD_REQUEST,
                "Database error",
                exception.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(BAD_REQUEST).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> illegalArgumentException(final IllegalArgumentException exception,
                                                                  final HttpServletRequest request) {
        final StandardError error = new StandardError(
                Instant.now(),
                BAD_REQUEST,
                "Illegal argument",
                exception.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(BAD_REQUEST).body(error);
    }
}
