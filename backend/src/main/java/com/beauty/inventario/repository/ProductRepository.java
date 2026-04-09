package com.beauty.inventario.repository;

import com.beauty.inventario.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}