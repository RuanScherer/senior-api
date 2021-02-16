package com.challenge.senior.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(final ResourceNotFoundException exception,
                                                          final HttpServletRequest request) {
        final HttpStatus status = HttpStatus.NOT_FOUND;
        final StandardError error = new StandardError(
                Instant.now(),
                status.value(),
                "Resource not found",
                exception.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(error);
    }
}
