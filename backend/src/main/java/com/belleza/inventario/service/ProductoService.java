package com.belleza.inventario.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.belleza.inventario.dto.ProductoRequestDTO;
import com.belleza.inventario.entity.Producto;
import com.belleza.inventario.repository.ProductoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository repository;

    public List<Producto> listar() {
        return repository.findAll();
    }

    public Producto guardar(ProductoRequestDTO dto) {

        Producto p = new Producto();
        p.setNombre(dto.getNombre());
        p.setMarca(dto.getMarca());
        p.setPrecio(dto.getPrecio());
        p.setStock(dto.getStock());

        return repository.save(p);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}