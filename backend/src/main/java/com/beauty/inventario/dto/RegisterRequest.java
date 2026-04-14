package com.beauty.inventario.dto;

public class RegisterRequest {

    private String email;
    private String password;
    private Double balance;
    private Long roleId;

    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public Double getBalance() { return balance; }
    public Long getRoleId() { return roleId; }
}