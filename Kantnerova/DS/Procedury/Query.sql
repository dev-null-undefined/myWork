-- a) Vytvořte proceduru se vstupními parametry, která umožní přidat osobu do databáze
-- Pridej_osobu(<prijmeni>,<jmeno>,<nazev_profese>)

CREATE PROCEDURE Pridej_osobu
    @prijmeni varchar(20),
    @jmeno varchar(20),
    @nazev_profese varchar(10)
AS
INSERT INTO osoba
    (primeni,jmeno,profese_id)
VALUES
    (@prijmeni, @jmeno, (SELECT id
        FROM profese
        WHERE profese.nazev=@nazev_profese))
exec Pridej_osobu  'Hromádka', 'Josef', 'Kuchař';

-- b) Vytvořte proceduru se vstupními parametry, která umožní změnit plat u osoby
-- Zmena_platu(<id_osoba>,<plat>)

CREATE PROCEDURE Zmena_platu
    @id_osoba int,
    @plat int
AS
UPDATE osoba SET plat=@plat WHERE osoba.id=@id_osoba

exec Zmena_platu 2, 15000;