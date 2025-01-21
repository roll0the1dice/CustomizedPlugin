package com.example.custom_plugin.service;

/**
 * This is a generated NotFoundException for demonstration purposes.
 */
public class TeamNotFoundException extends RuntimeException {
    public TeamNotFoundException() {
    }

    public TeamNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}