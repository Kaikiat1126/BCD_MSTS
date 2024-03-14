CREATE SCHEMA bcd;
USE bcd;

-- Create User table
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
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

INSERT INTO users (username, role, contact_number, email, password)
VALUES
    ('Bryan', 1, '1234567890', 'bryan@mail.com', 'password123'),
    ('Lennon', 2, '9876543210', 'lennon@mail.com', 'securepass'),
    ('KaiKiat', 3, '5678901234', 'kk@mail.com', 'pass123'),
    ('PheyMin', 4, '9012345678', 'pm@mail.com', 'userpass'),
    ('Yannis', 1, '3456789012', 'yannis@mail.com', 'mysecretpass'),
    ('Seng Feng', 2, '6789012345', 'sengfeng@mail.com', 'strongpassword'),
    ('Marco', 3, '7890123456', 'marco@mail.com', 'securepass123'),
    ('Innis', 4, '2345678901', 'innis@mail.com', 'mypassword'),
    ('John', 1, '4567890123', 'john@mail.com', 'pass1234'),
    ('Jane', 2, '8901234567', 'jane@mail.com', 'password789');

INSERT INTO medicine (name, type, manufacturer_id, price)
VALUES
    ('Paracetamol', 'Tablet', 1, 5.99),
    ('Amoxicillin', 'Capsule', 5, 8.50),
    ('Aspirin', 'Tablet', 9, 3.75),
    ('Ibuprofen', 'Tablet', 1, 6.25),
    ('Omeprazole', 'Capsule', 5, 10.99);

INSERT INTO inventory (user_id, medicine_id, quantity, batch_number)
VALUES
    (1, 1, 100, 1),
    (5, 2, 50, 1),
    (9, 3, 75, 1),
    (1, 4, 80, 1),
    (5, 4, 60, 1);