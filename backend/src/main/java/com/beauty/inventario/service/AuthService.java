package com.beauty.inventario.service;

import com.beauty.inventario.dto.*;
import com.beauty.inventario.entity.User;
import com.beauty.inventario.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String register(RegisterRequest req) {

        if (userRepository.findByEmail(req.getEmail()).isPresent()) {
            throw new RuntimeException("Email ya existe");
        }

        User user = new User();
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setBalance(req.getBalance());
        user.setRoleId(req.getRoleId());

        userRepository.save(user);

        return "Registro exitoso";
    }

    public LoginResponse login(LoginRequest req) {

        Optional<User> userOpt = userRepository.findByEmail(req.getEmail());

        if (userOpt.isEmpty()) {
            throw new RuntimeException("Usuario no existe");
        }

        User user = userOpt.get();

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("Password incorrecto");
        }

        String token = jwtService.generateToken(
                user.getId(),
                user.getEmail(),
                user.getRoleId()
        );

        LoginResponse res = new LoginResponse();
        res.setToken(token);

        return res;
    }
}