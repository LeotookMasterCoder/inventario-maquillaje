package com.beauty.inventario.security;

import com.beauty.inventario.service.JwtService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class JwtFilter implements Filter {

    private final JwtService jwtService;

    public JwtFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String header = req.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {

            String token = header.replace("Bearer ", "");

            try {
                jwtService.extractEmail(token);
            } catch (Exception e) {
                throw new RuntimeException("Invalid token");
            }
        }

        chain.doFilter(request, response);
    }
}