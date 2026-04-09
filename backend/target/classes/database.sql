CREATE DATABASE inventario_beauty;
USE inventario_beauty;

CREATE TABLE roles (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE users (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
email VARCHAR(100) NOT NULL UNIQUE,
password VARCHAR(255) NOT NULL,
role_id BIGINT,
FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE products (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(100) NOT NULL,
price DOUBLE NOT NULL
);

CREATE TABLE purchases (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
date DATETIME NOT NULL,
user_id BIGINT,
product_id BIGINT,
quantity INT NOT NULL,

FOREIGN KEY (user_id) REFERENCES users(id),
FOREIGN KEY (product_id) REFERENCES products(id)
);

INSERT INTO roles (name) VALUES ('CLIENT');
INSERT INTO roles (name) VALUES ('WORKER');
INSERT INTO roles (name) VALUES ('ADMIN');

INSERT INTO users (email, password, role_id) VALUES
('cliente@test.com', '1234', 1),
('trabajador@test.com', '1234', 2),
('admin@test.com', '1234', 3);

INSERT INTO products (name, price) VALUES
('Shampoo', 15000),
('Labial', 20000),
('Crema facial', 35000);