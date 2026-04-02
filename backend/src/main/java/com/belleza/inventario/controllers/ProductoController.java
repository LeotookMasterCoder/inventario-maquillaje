package com.belleza.inventario.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.belleza.inventario.dto.ProductoRequestDTO;
import com.belleza.inventario.entity.Producto;
import com.belleza.inventario.service.ProductoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService service;

    @GetMapping
    public List<Producto> listar() {
        return service.listar();
    }

    @PostMapping
    public Producto guardar(@RequestBody ProductoRequestDTO dto) {
        return service.guardar(dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}