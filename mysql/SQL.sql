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
    manufacturer_id VARCHAR(255),
    price DECIMAL(10, 2),
    FOREIGN KEY (manufacturer_id) REFERENCES users(user_id)
);

-- Create Inventory table
CREATE TABLE inventory (
    inventory_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(255),
    medicine_id INT,
    quantity INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (medicine_id) REFERENCES medicine(medicine_id)
);

CREATE TABLE transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    transaction_date DATE,
    sender VARCHAR(255),
    receiver VARCHAR(255),
    medicine_id INT,
    quantity INT,
    batch_number VARCHAR(255),
    sub_batch_number VARCHAR(255),
    production_date DATE,
    expiry_date DATE,
    additional_info TEXT,
    digital_signature VARCHAR(255)
);

INSERT INTO `users` VALUES ('384e7bab-001e-34b5-9058-3cb021d5b80f','Kaikiat',1,'123456789','kaikiat@gmail.com','839uNKwmWwjkTPmU16phlg==');