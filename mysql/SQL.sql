CREATE SCHEMA bcd;
USE bcd;

-- Create User table
CREATE TABLE users (
    user_id VARCHAR(255) PRIMARY KEY,
    username VARCHAR(255),
    role INT,
    contact_number VARCHAR(20),
    email VARCHAR(255),
    password VARCHAR(255)
);

-- Create Medicine table
CREATE TABLE medicine (
    medicine_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    type VARCHAR(255),
    manufacturer_id INT,
    price DECIMAL(10, 2),
    FOREIGN KEY (manufacturer_id) REFERENCES users(user_id)
);

-- Create Inventory table
CREATE TABLE inventory (
    inventory_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    medicine_id INT,
    quantity INT,
    batch_number INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (medicine_id) REFERENCES medicine(medicine_id)
);