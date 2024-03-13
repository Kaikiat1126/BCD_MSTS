CREATE SCHEMA bcd;
USE bcd;

-- Create User table
CREATE TABLE User (
    userID INT AUTO_INCREMENT PRIMARY KEY,
    userName VARCHAR(255),
    role ENUM('Manufacturer', 'Distributor', 'Healthcare Organisation', 'Patient'),
    contactNumber VARCHAR(20),
    email VARCHAR(255),
    password VARCHAR(255)
);

-- Create Medicine table
CREATE TABLE Medicine (
    medicineID INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    type VARCHAR(255),
    manufacturerID INT,
    price DECIMAL(10, 2),
    FOREIGN KEY (manufacturerID) REFERENCES User(userID)
);

-- Create Inventory table
CREATE TABLE Inventory (
    inventoryID INT AUTO_INCREMENT PRIMARY KEY,
    userID INT,
    medicineID INT,
    quantity INT,
    batchNumber INT,
    FOREIGN KEY (userID) REFERENCES User(userID),
    FOREIGN KEY (medicineID) REFERENCES Medicine(medicineID)
);

INSERT INTO User (userName, role, contactNumber, email, password)
VALUES
    ('Bryan', 'Manufacturer', '1234567890', 'bryan@example.com', 'password123'),
    ('Lennon', 'Distributor', '9876543210', 'lennon@example.com', 'securepass'),
    ('KK', 'Healthcare Organisation', '5678901234', 'kk@example.com', 'pass123'),
    ('PM', 'Patient', '9012345678', 'pm@example.com', 'userpass'),
    ('Yannis', 'Manufacturer', '3456789012', 'yannis@example.com', 'mysecretpass'),
    ('Seng Feng', 'Distributor', '6789012345', 'sengfeng@example.com', 'strongpassword'),
    ('Marco', 'Healthcare Organisation', '7890123456', 'marco@example.com', 'securepass123'),
    ('Innis', 'Patient', '2345678901', 'innis@example.com', 'mypassword'),
    ('John', 'Manufacturer', '4567890123', 'john@example.com', 'pass1234'),
    ('Jane', 'Distributor', '8901234567', 'jane@example.com', 'password789');

INSERT INTO Medicine (name, type, manufacturerID, price)
VALUES
    ('Paracetamol', 'Tablet', 1, 5.99),
    ('Amoxicillin', 'Capsule', 5, 8.50),
    ('Aspirin', 'Tablet', 9, 3.75),
    ('Ibuprofen', 'Tablet', 1, 6.25),
    ('Omeprazole', 'Capsule', 5, 10.99);

INSERT INTO Inventory (userID, medicineID, quantity, batchNumber)
VALUES
    (1, 1, 100, 1),
    (5, 2, 50, 1),
    (9, 3, 75, 1),
    (1, 4, 80, 1),
    (5, 4, 60, 1);