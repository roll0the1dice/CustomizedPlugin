package com.example.custom_plugin.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This is a generated BaseService for demonstration purposes.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WithAuth {
    /** This is an example modelAssembler. */
    UserRoleEnum mustRole();
}