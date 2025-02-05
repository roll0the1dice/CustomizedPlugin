package com.example.custom_plugin.service;

/**
 * This is a generated NotFoundException for demonstration purposes.
 */
public class UsersNotFoundException extends RuntimeException {
    public UsersNotFoundException() {
    }

    public UsersNotFoundException(Long id) {
        super("Could not find Users " + id);
    }
}