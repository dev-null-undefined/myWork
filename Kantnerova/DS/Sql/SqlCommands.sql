create database_name;
use database_name;
drop table lekar;
drop table specializace;

create table table_name(
    id int primary key identity(1,1),
    nazev varchar(10) not null unique
);
create table specializace(
    id int primary key identity(1,1),
    nazev varchar(10) not null unique
);
create table lekar(
    id int primary key identity(1,1),
    jmeno varchar(20) not null,
    prijmeni varchar(20) not null,
	datum_nastupu date,
	plat decimal(10,2) not null,
	specializace_id int foreign key references  specializace(id),
);
insert into specializace(nazev)values('Zubar');
insert into specializace(nazev)values('Ocni');
select * from specializace;


create table zam(
id int primary key identity(1,1),
jmeno varchar(20) not null,
prijmeni varchar(20) not null,
email varchar(50) not null,
dat_nar date
)

select * from zam;
insert into zam (jmeno, prijmeni, email, dat_nar) values ('Granger', 'Nancarrow', 'gnancarrow0@nhs.uk', '1981-08-14');
insert into zam (jmeno, prijmeni, email, dat_nar) values ('Harvey', 'Tiptaft', 'htiptaft1@seattletimes.com', '1950-12-20');
insert into zam (jmeno, prijmeni, email, dat_nar) values ('Ced', 'Pardon', 'cpardon2@ask.com', '1947-03-19');
insert into zam (jmeno, prijmeni, email, dat_nar) values ('Radcliffe', 'Attack', 'rattack3@bing.com', '1945-03-02');
insert into zam (jmeno, prijmeni, email, dat_nar) values ('Tabor', 'Plunkett', 'tplunkett4@nature.com', '1974-07-11');
insert into zam (jmeno, prijmeni, email, dat_nar) values ('Freda', 'Fishbourn', 'ffishbourn5@linkedin.com', '1967-04-28');
insert into zam (jmeno, prijmeni, email, dat_nar) values ('Taryn', 'Thurborn', 'tthurborn6@wix.com', '1998-06-15');
insert into zam (jmeno, prijmeni, email, dat_nar) values ('Elfrieda', 'Sheryn', 'esheryn7@artisteer.com', '1987-01-26');
insert into zam (jmeno, prijmeni, email, dat_nar) values ('Frances', 'Geistmann', 'fgeistmann8@studiopress.com', '1943-03-24');
insert into zam (jmeno, prijmeni, email, dat_nar) values ('Dennie', 'Labrum', 'dlabrum9@mozilla.com', '1967-06-12');
select * from zam;

ALTER TABLE zam ADD pozice varchar(20);

update zam set pozice='uklizec';

update zam set pozice='sefisko' where dat_nar<'1950-01-01';


ALTER TABLE zam ADD plat int;

update zam set plat='40000' where pozice='sefisko';

update zam set plat='25000' where pozice='uklizec';

/*delete from zam where email='borec@nejveci.cz';*/


/* Aktualni datum a rok 
select year(getdate());

*/

/* Scitnani dvou datumu date + 
DATEADD(<Unit of time>, <Units>, <Date>)

-- to add 5 days to September 1, 2011 the function would be
DATEADD(DAY, 5, '9/1/2011')
*/

select *,DATEDIFF(YYYY,dat_nar,CONVERT(date, getdate())) as Vek from zam order by Vek;

https://mockaroo.com/