package com.beauty.inventario.config;

import com.beauty.inventario.security.JwtFilter;
import com.beauty.inventario.service.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

@Configuration
public class SecurityConfig {

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilter(JwtService jwtService) {

        JwtFilter filter = new JwtFilter(jwtService);

        FilterRegistrationBean<JwtFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(filter);
        registration.addUrlPatterns("/products/*", "/purchase/*");

        return registration;
    }
}