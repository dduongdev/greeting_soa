package com.dduongdev.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Cấu hình gốc, chịu trách nhiệm quản lý bean cho business logic.
 */

@Configuration
@ComponentScan(basePackages = "com.dduongdev")
public class ApplicationConfig {

}
