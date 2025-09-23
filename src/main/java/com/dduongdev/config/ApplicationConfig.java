package com.dduongdev.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Cấu hình gốc, chịu trách nhiệm quản lý bean cho business logic.
 */

@Configuration
@ComponentScan(basePackages = "com.dduongdev")
@PropertySource(value = "classpath:application.properties")
public class ApplicationConfig {

}
