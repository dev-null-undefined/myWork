-- ****************** SqlDBM: MySQL ******************;
-- ***************************************************;
-- ************************************** `Folder`
DROP TABLE RealationTable;

DROP TABLE User;

DROP TABLE Permition;

DROP TABLE Folder;

CREATE TABLE `Folder` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nazev` varchar(50) NOT NULL,
  `Path` varchar(500) NOT NULL UNIQUE,
  `ParentId` int(11),
  PRIMARY KEY (`Id`),
  KEY `fkIdx_41` (`ParentId`),
  CONSTRAINT `FK_41` FOREIGN KEY `fkIdx_41` (`ParentId`) REFERENCES `Folder` (`Id`)
);

-- ************************************** `Permition`
CREATE TABLE `Permition` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Read` boolean NOT NULL DEFAULT false,
  `Write` boolean NOT NULL DEFAULT false,
  `Modify` boolean NOT NULL DEFAULT false,
  `Folder_id` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `Permition_ibfk_1` (`Folder_id`),
  CONSTRAINT `Permition_ibfk_1` FOREIGN KEY `Permition_ibfk_1` (`Folder_id`) REFERENCES `Folder` (`Id`)
);

-- ************************************** `User`
CREATE TABLE `User` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Jmeno` varchar(50) NOT NULL UNIQUE,
  `HashPassword` varchar(90) NOT NULL,
  `Admin` boolean NULL DEFAULT false,
  PRIMARY KEY (`Id`)
);

-- ************************************** `RealationTable`
CREATE TABLE `RealationTable` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Permition_id` int(11) NOT NULL,
  `User_id` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `RealationTable_ibfk_1` (`Permition_id`),
  CONSTRAINT `RealationTable_ibfk_1` FOREIGN KEY `RealationTable_ibfk_1` (`Permition_id`) REFERENCES `Permition` (`Id`),
  KEY `RealationTable_ibfk_2` (`User_id`),
  CONSTRAINT `RealationTable_ibfk_2` FOREIGN KEY `RealationTable_ibfk_2` (`User_id`) REFERENCES `User` (`Id`)
);

-- ************************************ `Insering users`
-- kokos5454heslo1234
-- MilujemeKarlicka
-- HesloJeJednaDva34
INSERT INTO
  User(Jmeno, HashPassword, Admin)
VALUES
  (
    'Admin',
    '$2y$10$4WYrTqo3Q2vu63.r7tb4rebSMMefhJJ/480wgrUisYHDVNQcKT8gm',
    TRUE
  );

INSERT INTO
  User(Jmeno, HashPassword, Admin)
VALUES
  (
    'Rodina',
    '$2y$10$eJs9DKdpS894jC5lad3GZusl/k/QcqJwe7Fueal/KcXvVSWexQIRS',
    false
  );

INSERT INTO
  User(Jmeno, HashPassword, Admin)
VALUES
  (
    'Pedro',
    '$2y$10$.5VS38jSVodTIyItVizoZ.doQ9/JISC6OPj5ZM286WJ38F.CXvTPC',
    false
  );

-- ************************************ `Insering folder`
INSERT INTO
  Folder(Nazev, Path, ParentId)
VALUES
  ('root', '/websiteFiles', NULL);

INSERT INTO
  Folder(Nazev, Path, ParentId)
VALUES
  ('FOTKY', '/websiteFiles/FOTKY', 1);

-- ************************************ `Insering Permition`
INSERT INTO
  Permition(`Read`, `Write`, `Modify`, `Folder_id`)
VALUES
  (TRUE, TRUE, false, 1);

-- ************************************ `Insering Releation`
INSERT INTO
  RealationTable(Permition_id, User_id)
VALUES
  (1, 1);

INSERT INTO
  RealationTable(Permition_id, User_id)
VALUES
  (1, 2);

INSERT INTO
  RealationTable(Permition_id, User_id)
VALUES
  (1, 3);
