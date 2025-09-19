package com.dduongdev.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Cấu hình Spring MVC, chịu trách nhiệm: + Kích hoạt tính năng của Spring MVC.
 * + Scan các @Controller + Cấu hình view resolver, static resources,...
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.dduongdev.controller")
public class WebMvcConfig implements WebMvcConfigurer {

}
