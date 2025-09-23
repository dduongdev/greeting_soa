package com.dduongdev.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import jakarta.servlet.Filter;

/**
 * Class khởi tạo ứng dụng web. Tạo và cấu hình DispatcherServlet của SpringMvc,
 * khởi động ApplicationContext và Servlet Application Context (cấu hình riêng
 * cho web layer), xác định url mapping cho DispatcherServlet.
 */

@Configuration
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { ApplicationConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebMvcConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] { new DelegatingFilterProxy("jwtRequestFilter") };
	}

}
