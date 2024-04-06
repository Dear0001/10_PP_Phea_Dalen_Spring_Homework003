package org.example.springhomework003.exception;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class AttendeeNotFoundException extends RuntimeException {
    public AttendeeNotFoundException(String message) {
        super(message);
    }
}
