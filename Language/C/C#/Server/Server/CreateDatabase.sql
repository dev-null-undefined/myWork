CREATE TABLE User
(
    Id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(255) NOT NULL UNIQUE,
    HashPassword VARCHAR(150) NOT NULL,
    isAdmin BOOLEAN DEFAULT FALSE
);