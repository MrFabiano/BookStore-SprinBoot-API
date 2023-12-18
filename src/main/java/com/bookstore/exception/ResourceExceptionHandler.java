package com.bookstore.exception;

import jakarta.servlet.ServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, ServletRequest request){
        StandardError standardError = new StandardError(System.currentTimeMillis(),
                HttpStatus.NOT_FOUND.value(),
                e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }

    @ExceptionHandler(DataIntegrationViolationException.class)
    public ResponseEntity<StandardError> dataIntegrationBooksAssociated(DataIntegrationViolationException e, ServletRequest request){
        StandardError standardError = new StandardError(System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> fieldMessageValidationError(MethodArgumentNotValidException e, ServletRequest request){
        ValidationError standardError = new ValidationError(System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "error field validation");

        for(FieldError x : e.getBindingResult().getFieldErrors()){
            standardError.addErrors(x.getField(), x.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }

}
