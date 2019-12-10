jestrab.kolej.mff.cuni.cz
show databases;
show tables;

/*Adding column
ALTER TABLE Zvire
ADD COLUMN Jmeno VARCHAR
(20) AFTER Nazev;*/

/*Simple select with Joining*/
SELECT *
FROM Zvire
  INNER JOIN Druh ON Zvire.Druh=Druh.Id;

/*MySql show Collumns from table*/
show columns from Zvire;

/*Update value
update Zvire set Druh=2 where Id=2;*/

/*Reference Integrity
ALTER TABLE Zvire add foreign key (Druh) REFERENCES Druh(Id);

/*Adding column
ALTER TABLE Viden
Add COLUMN Osoba int not null;*/

/*Reference Integrity
ALTER TABLE Viden
add foreign key (Osoba) REFERENCES Osoba(Id);*/

/*Better select with more complex Join*/
SELECT Zvire.Id, Zvire.Nazev, Jmeno, Druh.Nazev, Nohy, Potrava
FROM Zvire INNER JOIN Druh ON Zvire.Druh=Druh.Id
ORDER BY Zvire.Id;

/*Cool Select with all data that we know*/
SELECT Osoba.Jmeno, Viden.Cas, Zvire.Nazev, Zvire.Jmeno
as `Jmeno zvirete`, Druh.Nazev as Druh, Nohy, Potrava
FROM Viden
  INNER JOIN Zvire ON Zvire.Id=Viden.IdZvire
  INNER JOIN Druh ON Druh.Id=Zvire.Druh
  INNER JOIN Osoba ON Osoba.Id=Viden.Osoba
ORDER BY Viden.Osoba;
/*Nebo*/
SELECT *
FROM Viden
  INNER JOIN Zvire ON Zvire.Id=Viden.IdZvire
  INNER JOIN Druh ON Druh.Id=Zvire.Druh
  INNER JOIN Osoba ON Osoba.Id=Viden.Osoba
ORDER BY Viden.Osoba;

/*nidko nevidel*/
SELECT *
from Zvire  
WHERE NOT EXISTS(SELECT *
from Viden
where Viden.IdZvire=Zvire.Id);
/*secound option*/
SELECT *
from Zvire
  LEFT JOIN Viden ON Viden.IdZvire=Zvire.Id
WHERE Viden.Cas is null;

/*Seznam koho kdo vydel*/
SELECT Zvire.Jmeno, Osoba.Jmeno, Viden.Cas
from Zvire
  INNER JOIN Viden ON Viden.IdZvire=Zvire.Id
  INNER JOIN Osoba ON Osoba.Id=Viden.Osoba
ORDER BY Zvire.Id;
/*upgrade*/
SELECT Zvire.Jmeno, GROUP_CONCAT(Osoba.Jmeno SEPARATOR
',') as Videno
from Zvire
  INNER JOIN Viden ON Viden.IdZvire=Zvire.Id
  INNER JOIN Osoba ON Osoba.Id=Viden.Osoba
GROUP BY Zvire.Id;

/*Seznam zvirat + pocet videni*/
SELECT Zvire.Jmeno, COUNT(Viden.IdZvire)
from Zvire
  LEFT JOIN Viden ON Viden.IdZvire=Zvire.Id
  LEFT JOIN Osoba ON Osoba.Id=Viden.Osoba
GROUP BY Zvire.Id;


/*Reset AUTO_INCREMENT
ALTER TABLE tablename AUTO_INCREMENT = 1*/

SELECT Zvire.Jmeno, Osoba.Jmeno, Viden.Cas from Zvire INNER JOIN Viden ON Viden.IdZvire=Zvire.Id INNER JOIN Osoba ON Osoba.Id=Viden.Osoba ORDER BY Zvire.Id;

/*MySql index 
ALTER TABLE TABLE_NAME ADD INDEX (COLUMN_NAME);

ALTER TABLE Zvire ADD INDEX (Jmeno);
*/

select Osoba.Jmeno,Concat("+",Telefony.State," ",Telefony.Number) as Telefon,Viden.Cas,Zvire.Nazev as Zviratko,Zvire.Jmeno as Jmeno,Druh.Nazev as Druh,Zvire.Nohy as "Pocet noh",Zvire.Potrava as Potrava
from Viden
INNER Join Osoba on Osoba.Id=Viden.Osoba
INNER JOIN Zvire on Zvire.Id=Viden.IdZvire
INNER Join Druh on Druh.Id=Zvire.Druh
LEFT Join Telefony on Osoba.Telefon=Telefony.Id
ORDER by Osoba.Jmeno;

select *
from Viden
INNER Join Osoba on Osoba.Id=Viden.Osoba
INNER JOIN Zvire on Zvire.Id=Viden.IdZvire
INNER Join Druh on Druh.Id=Zvire.Druh
LEFT Join Telefony on Osoba.Telefon=Telefony.Id
ORDER by Osoba.Jmeno;