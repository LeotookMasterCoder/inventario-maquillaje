package com.beauty.inventario.controller;

import com.beauty.inventario.entity.Product;
import com.beauty.inventario.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository repo;

    @PostMapping
    public Product create(@RequestBody Product p) {
        return repo.save(p);
    }

    @GetMapping
    public List<Product> list() {
        return repo.findAll();
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product p) {
        Product product = repo.findById(id).orElseThrow();
        product.setName(p.getName());
        product.setPrice(p.getPrice());
        product.setStock(p.getStock());
        return repo.save(product);
    }
}