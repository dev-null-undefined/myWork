
CREATE TABLE Osoba(
  Id int IDENTITY(1, 1) PRIMARY KEY,
  Jmeno VARCHAR (20) NOT NULL,
  Prijmeni VARCHAR (20)
);
CREATE TABLE Model(
  Id int IDENTITY(1, 1) PRIMARY KEY,
  Nazev VARCHAR (20) NOT NULL,
  Vykon NUMERIC(10, 2),
  spotreva NUMERIC(10, 2)
);
CREATE TABLE Vozidlo(
  Id int IDENTITY(1, 1) PRIMARY KEY,
  spz VARCHAR (20) NOT NULL UNIQUE,
  date_made date NOT NULL,
  km int,
  ModelId int not null,
  FOREIGN KEY (ModelId) REFERENCES Model(Id)
);
CREATE TABLE Pronajem(
  Id int IDENTITY(1, 1) PRIMARY KEY,
  frome date NOT NULL,
  [to] date,
  OsobaId int not null,
  VozidloId int not null,
  FOREIGN KEY (OsobaId) REFERENCES Osoba(Id),
  FOREIGN KEY (VozidloId) REFERENCES Vozidlo(Id)
);
CREATE TABLE Telefon(
  Id int IDENTITY(1, 1) PRIMARY KEY,
  Cislo VARCHAR (9) NOT NULL,
  Predvolba VARCHAR (4),
  OsobaId int not null,
  FOREIGN KEY (OsobaId) REFERENCES Osoba(Id)
);

ALTER TABLE Vozidlo ALTER COLUMN date_made date;

insert into Model (Nazev,Vykon,spotreva) values ('FERARRRI',4.5,150);
insert into Model (Nazev,Vykon,spotreva) values ('PORSGE',9,250);
insert into Model (Nazev,Vykon,spotreva) values ('MALUCH',-1,0);
insert into Model (Nazev,Vykon,spotreva) values ('MALUCH_v2.0',2,0);
select * from Model;
insert into Vozidlo (spz,date_made,km,ModelId) values ('1234SPZ',null,1000000,3);
insert into Vozidlo (spz,date_made,km,ModelId) values ('MojeKara2','2008-11-11',90,1);
insert into Vozidlo (spz,date_made,km,ModelId) values ('MojeKara3','2008-11-11',90,1);
insert into Vozidlo (spz,date_made,km,ModelId) values ('MojeKara4','2008-11-11',90,1);
insert into Vozidlo (spz,date_made,km,ModelId) values ('MojeKara5','2008-11-11',90,1);

insert into Osoba(Jmeno,Prijmeni) values ('Karel','Nevim');
insert into Osoba(Jmeno,Prijmeni) values ('Kare123','Nevim');
insert into Osoba(Jmeno,Prijmeni) values ('NAG','Nevim');
insert into Osoba(Jmeno,Prijmeni) values ('Asgf','Nevim');
insert into Osoba(Jmeno,Prijmeni) values ('ewtewy','Nevim');


insert into Telefon(Cislo,Predvolba,OsobaId) values ('124564326','420',1);
insert into Telefon(Cislo,Predvolba,OsobaId) values ('124564326','420',1);
insert into Telefon(Cislo,Predvolba,OsobaId) values ('124564326','420',2);


insert into Pronajem (frome,[to],OsobaId,VozidloID) values ('2008-11-11',null,1,1);
insert into Pronajem (frome,[to],OsobaId,VozidloID) values ('2008-11-11',null,2,4);
insert into Pronajem (frome,[to],OsobaId,VozidloID) values ('2008-11-11',null,2,1);
insert into Pronajem (frome,[to],OsobaId,VozidloID) values ('2008-11-11',null,4,3);
insert into Pronajem (frome,[to],OsobaId,VozidloID) values ('2008-11-11',null,5,5);

select Jmeno,Cislo,Predvolba,frome,[to],Nazev,spz,date_made,km,Vykon,spotreva from Pronajem inner join Vozidlo on Vozidlo.Id=Pronajem.VozidloId inner join Model on Model.Id=Vozidlo.ModelId inner join Osoba on Pronajem.OsobaId=Osoba.Id left join Telefon on Telefon.OsobaId=Osoba.Id;
select * from Pronajem;
select * from Osoba;
select * from Vozidlo inner join Model on Model.Id=Vozidlo.ModelId;
select * from Telefon;