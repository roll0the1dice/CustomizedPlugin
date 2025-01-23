package com.example.custom_plugin.service;

/**
 * This is a generated NotFoundException for demonstration purposes.
 */
public class TestUserNotFoundException extends RuntimeException {
    public TestUserNotFoundException() {
    }

    public TestUserNotFoundException(Long id) {
        super("Could not find TestUser " + id);
    }
}