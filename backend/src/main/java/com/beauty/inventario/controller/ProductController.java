package com.beauty.inventario.controller;

import com.beauty.inventario.entity.Product;
import com.beauty.inventario.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Product save(@RequestBody Product product,
                        HttpServletRequest request) {

        String role = (String) request.getAttribute("role");

        if (!role.equals("WORKER") && !role.equals("ADMIN")) {
            throw new RuntimeException("Access denied");
        }

        return service.save(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id,
                       HttpServletRequest request) {

        String role = (String) request.getAttribute("role");

        if (!role.equals("ADMIN")) {
            throw new RuntimeException("Access denied");
        }

        service.delete(id);
    }
}