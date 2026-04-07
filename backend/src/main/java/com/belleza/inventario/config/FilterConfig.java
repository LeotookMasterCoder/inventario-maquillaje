package com.belleza.inventario.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.belleza.inventario.filter.JwtValidationFilter;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<JwtValidationFilter> jwtFilter(JwtValidationFilter filter) {

        FilterRegistrationBean<JwtValidationFilter> registration = new FilterRegistrationBean<>();

        registration.setFilter(filter);
        registration.addUrlPatterns("/*");
        registration.setOrder(1);

        return registration;
    }
}