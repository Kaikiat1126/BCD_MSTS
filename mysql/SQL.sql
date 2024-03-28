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
INSERT INTO `transactions` VALUES (1,'2024-03-29','384e7bab-001e-34b5-9058-3cb021d5b80f','',1,100,'021f346c-22d1-3e64-b477-4236102ad83d','0','2024-03-29','2025-03-29','this is panadol','[115, 28, 105, -82, -26, -51, 35, -33, -29, -8, -6, 40, 56, -75, 75, -87, -113, -109, -10, 44, 84, -15, 4, 95, -18, 125, -104, 49, 22, 102, 16, 24, -102, 123, -10, 56, -41, 68, 43, -91, -119, 99, -3, 40, -93, -82, -50, 24, -64, 109, -20, -9, -90, 119, 93, 14, 83, -116, 117, 64, 121, 2, 102, -108, 27, 100, -83, -60, 108, -27, 17, 16, 109, -68, 104, -70, -58, 20, -44, -109, -24, -14, -117, -31, -111, 27, -99, -87, -101, 24, 123, 20, 68, -75, -116, 50, -105, -42, -127, 84, -114, -59, -2, -73, 21, 23, 52, 125, -90, 80, -122, 42, 23, 36, 112, 74, -21, 112, -77, -85, -1, 73, 126, -10, 51, -56, 63, 77, -75, 96, 67, -70, 116, 40, -47, -17, 16, -39, 78, 31, 108, 87, -82, 46, 16, -113, -108, 50, -105, 120, -120, -60, 21, 66, -96, 42, 117, 46, -73, 98, -2, -20, 9, 20, -91, 34, -71, -10, 92, -44, -127, -18, 33, 26, -79, 124, -58, -38, -6, 70, 102, -32, -62, -86, 42, -117, -48, -98, 21, -30, 105, -29, -62, -66, 59, 59, -54, -119, 15, 1, 29, 64, -127, 57, -21, 124, 95, -99, -33, 50, 99, 127, -72, -10, 126, 119, -8, 12, 29, -80, 95, -123, -79, 107, 4, -85, 65, -81, -55, -75, -90, -4, 12, -51, 68, -125, 0, 1, -90, 54, 106, -89, 52, -104, -101, 1, 58, 46, 5, -98, -110, 33, -6, 9, -59, 5]');
