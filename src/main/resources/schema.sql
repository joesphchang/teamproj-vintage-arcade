DROP DATABASE IF EXISTS arcade_finder;
CREATE DATABASE arcade_finder;
USE arcade_finder;

-- CONDITION TABLE
CREATE TABLE GameCondition (
    conditionId INT AUTO_INCREMENT PRIMARY KEY,
    status VARCHAR(50) NOT NULL
);

-- MANUFACTURER TABLE
CREATE TABLE Manufacturer (
    manufacturerId INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    country VARCHAR(100),
    foundedYear INT
);


-- VENUE TABLE
CREATE TABLE Venue (
    venueId INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    location VARCHAR(255) NOT NULL,
    openFrom TIME NOT NULL,
    openTo TIME NOT NULL
);

-- CABINET TABLE (NO venueId!)
CREATE TABLE Cabinet (
    gameId INT AUTO_INCREMENT PRIMARY KEY,
    gameName VARCHAR(150) NOT NULL,
    year INT,
    pricePerPlay DECIMAL(5,2),

    conditionId INT NOT NULL,
    manufacturerId INT,

    FOREIGN KEY (conditionId) REFERENCES GameCondition(conditionId),
    FOREIGN KEY (manufacturerId) REFERENCES Manufacturer(manufacturerId)
        ON DELETE SET NULL
);


-- JOIN TABLE (MANY-TO-MANY)
CREATE TABLE VenueCabinet (
    venueId INT NOT NULL,
    gameId INT NOT NULL,

    PRIMARY KEY (venueId, gameId),

    FOREIGN KEY (venueId) REFERENCES Venue(venueId)
        ON DELETE CASCADE,

    FOREIGN KEY (gameId) REFERENCES Cabinet(gameId)
        ON DELETE CASCADE
);