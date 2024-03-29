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
    batch_number VARCHAR(255),
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

INSERT INTO `users` VALUES ('384e7bab-001e-34b5-9058-3cb021d5b80f','Kaikiat',1,'123456789','kaikiat@gmail.com','839uNKwmWwjkTPmU16phlg=='),('c6900fe3-4f5d-340a-b0a2-c5b22b561838','Lennon',2,'124567893','lennon@gmail.com','Oghx9JjS2+Lw4Wl4Sfm3pg==');
INSERT INTO `medicine` VALUES (1,'Panadol','Drug','384e7bab-001e-34b5-9058-3cb021d5b80f',10.00),(2,'Ampicillin','Powder','384e7bab-001e-34b5-9058-3cb021d5b80f',100.00);
INSERT INTO `transactions` VALUES (1,'2024-03-29','384e7bab-001e-34b5-9058-3cb021d5b80f','',1,100,'021f346c-22d1-3e64-b477-4236102ad83d','0','2024-03-29','2025-03-29','this is panadol','[55, 57, 67, -37, -97, 126, -119, 66, -21, 97, 80, 37, -99, 111, 113, 22, 35, -89, 51, -36, -56, -99, 22, 106, -37, 105, -55, 44, 23, 120, 51, 97, 35, -113, -23, -35, -77, -128, -104, 4, 122, 9, -81, -114, -6, -50, -6, -99, 116, -29, -55, 45, 49, -117, -69, -61, 17, -70, -112, 94, -30, 61, 6, 66, 2, -25, 35, -14, -116, 106, -114, -93, 63, 107, 42, 13, 18, -121, 63, -25, -85, -22, -119, -5, 32, 122, 95, 124, -104, -80, -60, -82, 117, 94, 106, -109, -64, -86, -100, 4, 21, -62, 99, -128, 96, 103, 40, 122, 54, 18, 74, 77, -11, -105, 67, 90, 71, 51, -106, -59, 10, -34, 118, 63, -29, -100, 61, -75, -113, -50, 1, -66, 66, -98, -1, 79, -34, -40, 104, 32, -34, 85, 81, 106, -20, 105, 15, 36, 27, 73, -13, -4, -100, 122, 12, 58, -114, 83, 18, 98, -40, -29, -113, -13, -94, 100, 62, 23, -102, 60, -27, -73, -99, -29, 60, 19, 105, 85, -25, -112, 7, -1, -122, -56, 114, 26, -69, 17, -103, 54, -111, 77, 39, 89, -97, -21, -106, 111, 72, -21, 108, -27, 75, -42, -10, -115, -71, 67, 10, -105, 123, -117, 46, 27, -22, 6, 32, 34, -61, -65, 100, 77, -9, 5, -20, -87, -24, -80, 28, 100, 8, -7, 2, -95, 51, 38, -39, 51, 92, 108, 46, 41, 122, 95, -101, -79, -26, -56, 84, -79, 38, 41, 41, 113, 120, -83]'),(2,'2024-03-29','384e7bab-001e-34b5-9058-3cb021d5b80f','',2,50,'892eaf56-78ab-3543-ace5-536a784b4e36','0','2024-03-29','2025-03-29','ampicillin for injection','[1, -117, -126, -1, -127, 33, 89, -42, -5, -57, -83, -72, -100, -17, 68, 116, 54, -20, -37, 114, -2, -51, -27, 104, -87, 2, -36, -33, -26, -30, 61, 59, -71, -113, 66, 0, -86, -2, -53, 9, 127, 91, -84, 122, 79, -6, 37, -58, -11, -61, 85, 124, -54, -103, -123, -97, 89, -86, -90, -127, 56, -7, -87, -13, 112, -59, -2, -121, 60, 124, 58, -69, -23, 31, 91, -120, -28, -122, -23, -80, -43, -97, -82, 56, -7, -77, -107, -45, 114, 25, 39, 86, -49, 68, -12, -114, 16, 96, -26, -59, -55, 109, 107, -112, -17, 80, 79, 78, -40, 123, -7, -122, -124, 46, 91, 31, -112, 54, -67, 16, -5, -26, 8, 11, -122, -76, 22, 73, -104, 14, 47, 43, 41, 56, -18, -25, 74, 126, -50, 79, -80, -43, 62, -38, -21, 63, 27, 51, -23, 64, 28, 29, -59, -98, 59, 89, 52, -87, -96, -14, 27, -58, 14, -63, -3, 40, 41, 87, -71, 79, -114, -115, 41, -36, 27, -80, 3, -97, 105, 66, 87, 51, -89, -2, 125, 117, 33, 55, -102, -75, -67, -111, -71, 111, -117, 24, 78, -35, -3, -115, -119, -28, 62, -35, -85, 44, -31, 21, -35, 14, -123, -28, -1, 126, -58, 75, 18, -19, 110, 84, -72, -120, -31, 92, -17, 48, -121, -109, 14, 97, 45, 84, -92, 23, -88, 94, -11, 3, -53, 11, -83, 64, 96, -39, 90, 102, -41, -42, -109, -103, -99, 54, 97, -86, 17, 63]');
INSERT INTO `inventory` VALUES (1,'384e7bab-001e-34b5-9058-3cb021d5b80f',1,100,'021f346c-22d1-3e64-b477-4236102ad83d'),(2,'384e7bab-001e-34b5-9058-3cb021d5b80f',2,50,'892eaf56-78ab-3543-ace5-536a784b4e36');



