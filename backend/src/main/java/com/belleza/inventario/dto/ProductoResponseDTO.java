package com.belleza.inventario.dto;

import lombok.Data;

@Data
public class ProductoResponseDTO {
    private Long id;
    private String nombre;
    private String marca;
    private Double precio;
    private Integer stock;
}