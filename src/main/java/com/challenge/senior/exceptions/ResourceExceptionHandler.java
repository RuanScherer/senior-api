package com.challenge.senior.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
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
    public ResponseEntity<StandardError> databaseException(final DatabaseException exception,
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

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<StandardError> invalidFormatException(final InvalidFormatException exception,
                                                                final HttpServletRequest request) {
        final StandardError error = new StandardError(
                Instant.now(),
                BAD_REQUEST,
                "Invalid format",
                "Some JSON attributes were sent with the wrong format.",
                request.getRequestURI()
        );
        return ResponseEntity.status(BAD_REQUEST).body(error);
    }

    @ExceptionHandler(PropertyValueException.class)
    public ResponseEntity<StandardError> propertyValueException(
            final PropertyValueException exception,
            final HttpServletRequest request
    ) {
        final StandardError error = new StandardError(
                Instant.now(),
                BAD_REQUEST,
                "Bad request",
                "Some required JSON attributes were not sent or references an unsaved transient instance.",
                request.getRequestURI()
        );
        return ResponseEntity.status(BAD_REQUEST).body(error);
    }
}
