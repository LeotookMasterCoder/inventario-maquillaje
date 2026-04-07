package com.belleza.inventario.controllers;

import org.springframework.web.bind.annotation.*;

import com.belleza.inventario.dto.MessageResponseDTO;
import com.belleza.inventario.service.JwtService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;

    @PostMapping("/login")
    public MessageResponseDTO login(@RequestParam String username) {

        String token = jwtService.generarToken(username);

        MessageResponseDTO response = new MessageResponseDTO();
        response.setMessage(token);

        return response;
    }
}