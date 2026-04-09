CREATE DATABASE inventario_beauty;
USE inventario_beauty;

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);

CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DOUBLE NOT NULL,
    stock INT NOT NULL
);

INSERT INTO users (username, password, role) VALUES (
    'admin',
    '$2a$10$Dow1j5YdQWzQ0nYqK0VY5uH3F5FhFhFhFhFhFhFhFhFhFhFhFhFhF',
    'ADMIN'
);