DROP DATABASE IF EXISTS restaurant;
CREATE DATABASE restaurant;
USE restaurant;

CREATE TABLE user (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone_number VARCHAR(20) NOT NULL,
    pwd VARCHAR(255) NOT NULL,
    type ENUM('admin', 'client', 'driver', 'chef') NOT NULL
);

CREATE TABLE product (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    image VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    descriotion 
    type ENUM('burger', 'drink', 'salchipapa', 'chaufa') NOT NULL
);

CREATE TABLE extra (
    extra_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE `order` (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    client_id INT NOT NULL,
    date DATETIME NOT NULL,
    address VARCHAR(255) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    state ENUM('on_hold', 'on_process', 'on_the_way', 'waiting_driver', 'finished', 'canceled') NOT NULL,
    evidence VARCHAR(255) NOT NULL,
    FOREIGN KEY (client_id) REFERENCES user(user_id)
);

CREATE TABLE order_detail (
    detail_id INT AUTO_INCREMENT,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL CHECK (quantity >= 1),
    extras JSON NOT NULL DEFAULT '[]',
    PRIMARY KEY (detail_id),
    FOREIGN KEY (order_id) REFERENCES `order`(order_id),
    FOREIGN KEY (product_id) REFERENCES product(product_id)
);