package org.example.springhomework003.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler({AllNotFoundException.class})
    public ProblemDetail handlerAllNotFoundException(AllNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setType(URI.create("about:blank"));
        problemDetail.setTitle("Venue Not Found");
        problemDetail.setStatus(404);
        problemDetail.setDetail(e.getMessage());
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        //storing field errors
        HashMap<String, String> errors = new HashMap<>();
        //catching error catching field
        for(FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        //showing error message on UI
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Invalid Request");
        problemDetail.setType(URI.create("about:blank"));
        problemDetail.setTitle("Bad Request");
        problemDetail.setProperty("errors", errors);
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ProblemDetail handleMethodValidationException(HandlerMethodValidationException e) {
        HashMap<String, String> errors = new HashMap<>();

        for (var parameterError : e.getAllValidationResults()) {
            String parameterName = parameterError.getMethodParameter().getParameterName();
            for (var error : parameterError.getResolvableErrors()) {
                errors.put(parameterName, error.getDefaultMessage());
            }
        }
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "Invalid request"
        );
        problemDetail.setTitle("Bad Request");
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        problemDetail.setProperty("error", errors);
        return problemDetail;
    }

}
