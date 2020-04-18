-- 4. vytvořte pohledy:
-- a) vytvořte pohled "seznam_zamestnancu", který umožní výpis všech osob (příjmení, jméno) včetně jejich profesí (název), seřazených abecedně podle profese, příjmení a jména
drop view seznam_zamestnancu

CREATE VIEW seznam_zamestnancu
AS
    SELECT osoba.jmeno, osoba.primeni, profese.nazev as profese
    from osoba
        inner join profese on profese.id=osoba.profese_id

-- b) vytvořte pohled "obsazeni_profesi", který umožní výpis všech profesí s počtem osob, které ji mají

drop view obsazeni_profesi

CREATE VIEW obsazeni_profesi
AS
    SELECT count(*) as pocet, profese.nazev as profese
    from osoba
        inner join profese on profese.id=osoba.profese_id
    group by profese.id,profese.nazev


-- 5. použijte uložené pohledy:
-- a) 1.pohled pro výpis zaměstnanců pouze určité jedné profese

select *
from seznam_zamestnancu
where profese = 'Číšník'

-- b) 2. pohled pro výpis profesí s počtem > 5 osob

select *
from obsazeni_profesi
where pocet > 5