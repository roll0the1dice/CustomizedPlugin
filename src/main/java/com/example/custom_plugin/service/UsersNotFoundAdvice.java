package com.example.custom_plugin.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * This is a generated NotFoundException for demonstration purposes.
 */
@RestControllerAdvice
public class UsersNotFoundAdvice {
    @ExceptionHandler(UsersNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String UsersNotFoundHandler(UsersNotFoundException ex) {
        return ex.getMessage();
    }
}