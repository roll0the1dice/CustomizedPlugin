package com.example.custom_plugin.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.hibernate.annotations.IdGeneratorType;

/**
 * This is a generated BaseService for demonstration purposes.
 */
@IdGeneratorType(SnowflakeIdGeneratorImpl.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SnowflakeIdGenerator {
    long workerId() default 1L;

    long datacenterId() default 1L;
}