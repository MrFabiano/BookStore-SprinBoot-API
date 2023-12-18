package com.bookstore.exception;

public class DataIntegrationViolationException extends RuntimeException {


    public DataIntegrationViolationException(String message, Throwable cause){
        super(message, cause);
    }

    public DataIntegrationViolationException(String message){
        super(message);
    }
}
