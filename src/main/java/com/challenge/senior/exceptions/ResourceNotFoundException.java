package com.challenge.senior.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(final Object id) {
        super("Resource not found. ID: " + id);
    }
}
