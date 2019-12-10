/*Tabulky*/
CREATE TABLE Druh(
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nazev` enum('Domaci','Divoke','Ochocene') DEFAULT NULL,
  PRIMARY KEY (`Id`)
);
CREATE TABLE Zvire (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nazev` varchar(50) NOT NULL,
  `Jmeno` varchar(20) DEFAULT NULL,
  `Druh` int(11) DEFAULT NULL,
  `Nohy` int(11) NOT NULL,
  `Potrava` enum('masozravec','bylozravec','vsezravec') DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `Druh` (`Druh`),
  CONSTRAINT `Zvire_ibfk_3` FOREIGN KEY (`Druh`) REFERENCES `Druh` (`Id`)
);
CREATE TABLE Telefony(
  `Id` int NOT null AUTO_INCREMENT,
  `Number` VARCHAR(9) not null,
  `State` varchar(3),
  primary key (`Id`)
);
CREATE TABLE Osoba(
  `Id` INT not null AUTO_INCREMENT,
  `Telefon` int,
  `Jmeno` VARCHAR(20),
  primary KEY (`Id`),
  key `Osoba_ibfk_3`(`Telefon`),
  CONSTRAINT `Osoba_ibfk_3` FOREIGN KEY (`Telefon`) REFERENCES `Telefony` (`Id`)
);
CREATE TABLE Viden(
  `IdZvire` int(11) NOT NULL,
  `Cas` datetime NOT NULL,
  `Osoba` int(11) NOT NULL,
  KEY `Viden_ibfk` (`IdZvire`),
  KEY `Viden_ibfk_1` (`Osoba`),
  CONSTRAINT `Viden_ibfk_1` FOREIGN KEY (`Osoba`) REFERENCES `Osoba` (`Id`),
  CONSTRAINT `Viden_ibfk` FOREIGN KEY (`IdZvire`) REFERENCES `Zvire` (`Id`)
);

/*Druhy*/
insert into Druh(Nazev)values("Domaci");
insert into Druh(Nazev)values("Divoke");
insert into Druh(Nazev)values("Ochocene");

/*Zvirata*/
INSERT INTO Zvire(Nazev,Jmeno,Druh,Nohy,Potrava)VALUES('Kocka', 'Angie', 1, 3, 'Vsezravec');
INSERT INTO Zvire(Nazev,Jmeno,Druh,Nohy,Potrava)VALUES('Kocka', 'Charlie', 2, 4, 'Masozravec');
INSERT INTO Zvire(Nazev,Jmeno,Druh,Nohy,Potrava)VALUES('Pes', 'Karel', 2, 4, 'Masozravec');
INSERT INTO Zvire(Nazev,Jmeno,Druh,Nohy,Potrava)VALUES('Kun', 'Stoupa', 3, 3, 'Bylozravec');
INSERT INTO Zvire(Nazev,Jmeno,Druh,Nohy,Potrava)VALUES('Opice', 'Petr', 2, 4, 'Vsezravec');
INSERT INTO Zvire(Nazev,Jmeno,Druh,Nohy,Potrava)VALUES('Zizala', 'Julie', 1, 0, 'Vsezravec');
INSERT INTO Zvire(Nazev,Jmeno,Druh,Nohy,Potrava)VALUES('Yetty', 'SnowMan', 1, 2, 'Vsezravec');

/*Telefony*/
insert into Telefony(Id,Number,State) values(1,735986705,420);
insert into Telefony(Id,Number,State) values(2,724334594,420);
insert into Telefony(Id,Number,State) values(3,602258332,420);

/*Osoby*/
INSERT INTO Osoba(Jmeno)VALUES("Misa");
INSERT INTO Osoba(Jmeno)VALUES("Verca");
INSERT INTO Osoba(Jmeno,Telefon)VALUES("Martin",1);
INSERT INTO Osoba(Jmeno,Telefon)VALUES("Zuzka",2);
INSERT INTO Osoba(Jmeno,Telefon)VALUES("Petr",3);

/*Viden*/
/*Charlie a Angie*/
INSERT INTO Viden(IdZvire,Cas,Osoba)VALUES(2, NOW(), 1);
INSERT INTO Viden(IdZvire,Cas,Osoba)VALUES(1, NOW(), 1);
INSERT INTO Viden(IdZvire,Cas,Osoba)VALUES(2, NOW(), 2);
INSERT INTO Viden(IdZvire,Cas,Osoba)VALUES(1, NOW(), 2);
INSERT INTO Viden(IdZvire,Cas,Osoba)VALUES(2, NOW(), 4);
INSERT INTO Viden(IdZvire,Cas,Osoba)VALUES(1, NOW(), 4);
INSERT INTO Viden(IdZvire,Cas,Osoba)VALUES(2, NOW(), 5);
INSERT INTO Viden(IdZvire,Cas,Osoba)VALUES(1, NOW(), 5);




/*Zbytek*/
INSERT INTO Viden(IdZvire,Cas,Osoba)VALUES(6, NOW(), 3);/*Martin julie*/
INSERT INTO Viden(IdZvire,Cas,Osoba)VALUES(5, NOW(), 5);/*Petr Opice*/
INSERT INTO Viden(IdZvire,Cas,Osoba)VALUES(3, NOW(), 3);/*Martin Karel*/
INSERT INTO Viden(IdZvire,Cas,Osoba)VALUES(4, NOW(), 2);/*Verca kone*/



/*DROP TABLES
drop table Viden;
drop table Osoba;
drop table Telefony;
drop table Zvire;
drop table Druh;
*/



CREATE TABLE Messege(
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `text` VARCHAR(250) not null,
  PRIMARY KEY (`Id`)
);