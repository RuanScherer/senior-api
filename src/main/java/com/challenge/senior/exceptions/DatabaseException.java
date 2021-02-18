package com.challenge.senior.exceptions;

public class DatabaseException extends RuntimeException{
    public DatabaseException(final String message) {
        super(message);
    }
}
