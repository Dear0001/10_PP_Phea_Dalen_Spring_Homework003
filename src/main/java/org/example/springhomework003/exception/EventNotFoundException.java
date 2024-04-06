package org.example.springhomework003.exception;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(String message) {
        super(message);
    }
}
