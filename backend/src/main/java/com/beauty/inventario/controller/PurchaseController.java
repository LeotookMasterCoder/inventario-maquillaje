package com.beauty.inventario.controller;

import com.beauty.inventario.entity.*;
import com.beauty.inventario.repository.*;
import com.beauty.inventario.service.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired private ProductRepository productRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private JwtService jwtService;

    @PostMapping("/{productId}")
    public String purchase(@PathVariable Long productId,
                           @RequestHeader("Authorization") String header) {

        String token = header.replace("Bearer ", "");

        String email = jwtService.extractEmail(token);

        User user = userRepository.findByEmail(email).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();

        if (product.getStock() <= 0) return "Out of stock";
        if (user.getBalance() < product.getPrice()) return "Insufficient balance";

        product.setStock(product.getStock() - 1);
        user.setBalance(user.getBalance() - product.getPrice());

        productRepository.save(product);
        userRepository.save(user);

        return "Purchase successful";
    }
}