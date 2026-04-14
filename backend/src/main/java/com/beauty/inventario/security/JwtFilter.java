package com.beauty.inventario.security;

import com.beauty.inventario.service.JwtService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        if (path.startsWith("/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            response.setStatus(401);
            response.getWriter().write("Missing token");
            return;
        }

        String token = header.substring(7);

        if (!jwtService.isTokenValid(token)) {
            response.setStatus(401);
            response.getWriter().write("Invalid token");
            return;
        }

        request.setAttribute("userId", jwtService.extractUserId(token));
        request.setAttribute("rolId", jwtService.extractRolId(token));

        filterChain.doFilter(request, response);
    }
}