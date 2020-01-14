CREATE TABLE jazyk(
    `Id` int (11) NOT NULL AUTO_INCREMENT,
    `Jazyk` VARCHAR (30) NOT NULL UNIQUE,
    PRIMARY KEY (`Id`)
);

CREATE TABLE slovo(
    `Id` int (11) NOT NULL AUTO_INCREMENT,
    `IdJazyk` int (11) NOT NULL,
    `Slovo` VARCHAR (500) NOT NULL,
    CONSTRAINT `fk_IdJazyk` FOREIGN KEY (`IdJazyk`) REFERENCES `jazyk` (`Id`),
    PRIMARY KEY (`Id`)
);

CREATE TABLE Preklad(
    `Id` int (11) NOT NULL AUTO_INCREMENT,
    `IdSlovo1` int (11) NOT NULL,
    `IdSlovo2` int (11) NOT NULL,
    CONSTRAINT `fk1_IdJazyk` FOREIGN KEY (`IdSlovo1`) REFERENCES `slovo` (`Id`),
    CONSTRAINT `fk2_IdJazyk` FOREIGN KEY (`IdSlovo2`) REFERENCES `slovo` (`Id`),
    PRIMARY KEY (`Id`)
);

SELECT
*
FROM
slovo
INNER join jazyk on slovo.IdJazyk=jazyk.Id
where slovo.Slovo="" and jazyk.Jazyk="";

insert into Preklad (IdSlovo1,IdSlovo2) VALUES ();


SELECT
*
FROM
Preklad
INNER JOIN slovo AS fromSlovo ON Preklad.IdSlovo1 = fromSlovo.Id
INNER JOIN slovo AS toSlovo ON Preklad.IdSlovo2 = toSlovo.Id
INNER JOIN jazyk AS fromJazyk ON fromSlovo.IdJazyk = fromJazyk.Id
INNER JOIN jazyk AS toJazyk ON toSlovo.IdJazyk = toJazyk.Id


ALTER TABLE slovo  
MODIFY Slovo varchar(1200) NOT NULL;  