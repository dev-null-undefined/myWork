CREATE TRIGGER tr_plat ON osoba
AFTER INSERT 
AS
BEGIN
    UPDATE osoba
SET plat = (select avg(plat)
    from osoba)
FROM inserted
WHERE osoba.id = inserted.id;
END

create table osoba_deleted
(
    id int primary key identity(1,1),
    primeni varchar(20) not null,
    jmeno varchar(20) not null,
    plat int,
    profese_id int not null foreign key references profese(id)
);

CREATE TRIGGER tr_zaloha ON osoba
INSTEAD OF DELETE
AS
BEGIN
    INSERT INTO osoba_deleted
        (primeni,jmeno,plat,profese_id)
    SELECT primeni, jmeno, plat, profese_id
    FROM deleted;
    DELETE FROM osoba where id in (select deleted.id
    from deleted)
END

select *
from osoba;
select *
from osoba_deleted;
delete from osoba where id <5;
exec Pridej_osobu  'Hromádka', 'Josef', 'Kuchař';