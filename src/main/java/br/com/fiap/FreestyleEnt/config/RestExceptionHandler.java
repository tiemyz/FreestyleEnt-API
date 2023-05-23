package br.com.fiap.FreestyleEnt.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.fiap.FreestyleEnt.models.RestValidationError;
import jakarta.validation.ConstraintViolationException;


@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<List<RestValidationError>> constraintViolationExceptionHandler(ConstraintViolationException e){
        List<RestValidationError> errors = new ArrayList<>();

        e.getConstraintViolations().forEach((v) -> { 
            errors.add(new RestValidationError(v.getPropertyPath().toString(), v.getMessage()));
        });

        return ResponseEntity.badRequest().body(errors);
    }
    
}