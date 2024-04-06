package org.example.springhomework003.exception;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class AllNotFoundException extends RuntimeException {
    public AllNotFoundException(String message) {
        super(message);
    }
}
