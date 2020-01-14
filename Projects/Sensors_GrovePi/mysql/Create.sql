CREATE TABLE Zaznam(
    `Id` int (11) NOT NULL AUTO_INCREMENT,
    `Time` datetime NOT NULL,
    `Temp` float(20,6),
    `Hum` float(20,6),
    `Lux` float(20,6),
    `Noise` float(20,6),
    PRIMARY KEY (`Id`)
);