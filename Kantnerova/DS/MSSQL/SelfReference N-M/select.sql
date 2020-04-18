select zapas.Datum,
    domaci.Id,
    domaci.Nazev as Domaci,
    Domaci_hraci=STUFF((SELECT ','+Jmeno
    FROM hrac
    WHERE hrac.TeamId=domaci.Id
    FOR XML PATH('')) , 1 , 1 , '' ),
    hoste.Nazev as Hoste,
    Hoste_hraci=STUFF((SELECT ','+Jmeno
    FROM hrac
    WHERE hrac.TeamId=hoste.Id
    FOR XML PATH('')) , 1 , 1 , '' ),
    CONCAT(Domaci_body, ':', zapas.Hoste_body) as 'Domaci:Hoste'
from zapas
    INNER JOIN team as domaci on domaci.Id=zapas.Domaci_id
    INNER JOIN team as hoste on hoste.Id=zapas.Hoste_id
    INNER JOIN hrac as hoste_hraci on hoste_hraci.TeamId=hoste.Id
    INNER JOIN hrac as domaci_hraci on domaci_hraci.TeamId=domaci.Id
ORDER BY Datum;

select COUNT(*) as PocetZapasu
from zapas;

select COUNT(*) as PocetTeamu
from team;

select COUNT(*) as PocetHracu
from hrac;

select AVG(Zapas.Hoste_body) as Prumer_Hoste_body, AVG(Zapas.Domaci_body) as Prumer_Domaci_body
from zapas INNER JOIN team as domaci on domaci.Id=zapas.Domaci_id INNER JOIN team as hoste on hoste.Id=zapas.Hoste_id;

select MAX(Zapas.Hoste_body) as MAX_Hoste_body, MAX(Zapas.Domaci_body) as MAX_Domaci_body
from zapas INNER JOIN team as domaci on domaci.Id=zapas.Domaci_id INNER JOIN team as hoste on hoste.Id=zapas.Hoste_id;

select MIN(Zapas.Hoste_body) as MIN_Hoste_body, MIN(Zapas.Domaci_body) as MIN_Domaci_body
from zapas INNER JOIN team as domaci on domaci.Id=zapas.Domaci_id INNER JOIN team as hoste on hoste.Id=zapas.Hoste_id;

select SUM(Zapas.Hoste_body) as SUM_Hoste_body, SUM(Zapas.Domaci_body) as SUM_Domaci_body
from zapas INNER JOIN team as domaci on domaci.Id=zapas.Domaci_id INNER JOIN team as hoste on hoste.Id=zapas.Hoste_id;

select domaci.Nazev as team, SUM(Zapas.Domaci_body)+SUM(Zapas.Hoste_body) as Teamove_body
from zapas INNER JOIN team as domaci on domaci.Id=zapas.Domaci_id INNER JOIN team as hoste on hoste.Id=zapas.Hoste_id
group by domaci.Id,domaci.Nazev;