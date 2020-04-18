create table profese
(
    id int primary key identity(1,1),
    nazev varchar(10) not null
);

create table osoba
(
    id int primary key identity(1,1),
    primeni varchar(20) not null,
    jmeno varchar(20) not null,
    plat int,
    profese_id int not null foreign key references profese(id)
);