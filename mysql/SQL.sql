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
    digital_signature mediumtext
);

INSERT INTO `users` VALUES ('384e7bab-001e-34b5-9058-3cb021d5b80f','Kaikiat',1,'123456789','kaikiat@gmail.com','839uNKwmWwjkTPmU16phlg==');
INSERT INTO `medicine` VALUES (1,'Panadol','Drug','384e7bab-001e-34b5-9058-3cb021d5b80f',10.00);
INSERT INTO `transactions` VALUES (1,'2024-03-29','384e7bab-001e-34b5-9058-3cb021d5b80f','',1,100,'021f346c-22d1-3e64-b477-4236102ad83d','0','2024-03-29','2025-03-29','this is panadol','[61, 27, -109, 8, -1, 36, -5, 28, 10, 28, -55, 94, 30, 33, 48, -73, 93, -84, -83, 82, 47, -110, -110, -94, 100, 101, -96, 25, -65, 74, -2, 34, 48, 98, 22, 0, -69, -86, 115, 121, 115, -8, 38, 117, 103, 7, -46, 65, 114, -123, 101, -99, -22, -116, -100, 112, -81, 103, 20, -4, -23, -113, 46, -6, -18, 59, 94, -86, 7, 95, 118, 60, 112, 98, 78, 6, 118, 32, -64, -50, -6, 7, -67, -66, -9, 86, -74, 61, 99, -90, 62, 42, 76, -88, -108, -56, 10, -3, 83, -100, -91, 41, 50, 80, -23, 104, -47, 17, 116, 78, -76, -15, 63, -81, -118, 38, -55, -48, -103, 26, 62, 63, -81, 38, 95, -14, -38, 118, -42, 84, -27, 42, -37, -116, 76, -7, 102, -98, -69, 60, 21, -26, 126, -78, 18, -86, 34, -128, 40, -3, 53, 111, -81, -81, -62, 115, 37, -8, -107, 89, 59, -11, 32, -83, 9, 23, -71, -5, -47, -24, 108, -60, -63, -58, -95, -37, -38, -70, -24, 122, 4, 116, 50, -83, 72, 16, 52, 81, 69, -127, -71, 13, 67, 102, 18, 102, 123, 28, -35, -72, 36, -105, -42, -86, 93, -11, -88, 78, -51, 84, -122, 44, 71, -105, -39, -48, 10, 7, 94, -19, -43, 85, -27, 32, -27, -46, 51, -78, -116, -102, -31, -116, 20, -20, 0, -106, -6, 28, 74, 99, -55, -78, 15, 8, -58, 79, 56, 113, -1, 4, -83, 13, -42, -110, 100, -18]');
