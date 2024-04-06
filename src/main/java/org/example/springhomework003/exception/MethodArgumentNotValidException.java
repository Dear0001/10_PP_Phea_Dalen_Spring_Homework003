package org.example.springhomework003.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MethodArgumentNotValidException extends RuntimeException{
    public MethodArgumentNotValidException(String message) {
        super(message);
    }

}
