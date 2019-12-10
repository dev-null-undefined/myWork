SELECT *
FROM Viden
  INNER JOIN Zvire ON Zvire.Id=Viden.IdZvire
  INNER JOIN Druh ON Druh.Id=Zvire.Druh
  INNER JOIN Osoba ON Osoba.Id=Viden.Osoba
ORDER BY Viden.Osoba;