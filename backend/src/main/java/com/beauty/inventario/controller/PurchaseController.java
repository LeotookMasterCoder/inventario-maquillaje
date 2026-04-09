package com.beauty.inventario.controller;

import com.beauty.inventario.dto.PurchaseRequest;
import com.beauty.inventario.entity.Purchase;
import com.beauty.inventario.service.PurchaseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    private final PurchaseService service;

    public PurchaseController(PurchaseService service) {
        this.service = service;
    }

    @PostMapping
    public Purchase create(@RequestBody PurchaseRequest request,
                           HttpServletRequest http) {

        String role = (String) http.getAttribute("role");

        if (!role.equals("CLIENT")) {
            throw new RuntimeException("Only clients can purchase");
        }

        return service.create(
                request.getUserId(),
                request.getProductId(),
                request.getQuantity()
        );
    }

    @GetMapping
    public List<Purchase> getAll(HttpServletRequest http) {

        String role = (String) http.getAttribute("role");

        if (!role.equals("WORKER") && !role.equals("ADMIN")) {
            throw new RuntimeException("Access denied");
        }

        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id,
                       HttpServletRequest http) {

        String role = (String) http.getAttribute("role");

        if (!role.equals("ADMIN")) {
            throw new RuntimeException("Only admin can delete");
        }

        service.delete(id);
    }
}