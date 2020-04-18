drop table zapas;
drop table hrac;
drop table team;
CREATE TABLE team
(
    Id int IDENTITY(1, 1) PRIMARY KEY,
    Nazev VARCHAR (40) NOT NULL UNIQUE CHECK(len(Nazev)>3)
);
CREATE TABLE hrac
(
    Id int IDENTITY(1, 1) PRIMARY KEY,
    Jmeno VARCHAR (40) NOT NULL CHECK(len(Jmeno)>2),
    Prijmenni VARCHAR (40) NOT NULL CHECK(len(Prijmenni)>2),
    TeamId int NOT NULL,
    FOREIGN KEY (TeamId) REFERENCES team(Id)
);
CREATE TABLE zapas(
    Id int IDENTITY(1, 1) PRIMARY KEY,
    Domaci_id int NOT NULL,
    Hoste_id int NOT NULL,
    Datum date NOT NULL CHECK (Datum<GetDate()),
    Hoste_body int NOT NULL CHECK (Hoste_body>=0 and Hoste_body <= 25),
    Domaci_body int NOT NULL CHECK (Domaci_body>=0 and Domaci_body <=25)
    FOREIGN KEY (Domaci_id) REFERENCES team(Id),
    FOREIGN KEY (Hoste_id) REFERENCES team(Id)
);
