CREATE TABLE Zaznam(
    `Id` int (11) NOT NULL AUTO_INCREMENT,
    `Time` datetime NOT NULL,
    `Temp` float(6,3),
    `Hum` int(6),
    `Lux` int(6),
    `Noise` int(6),
    PRIMARY KEY (`Id`)
);