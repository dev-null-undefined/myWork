select *
from prodej inner join zakaznik on prodej.zak_id=zakaznik.id inner join auto on auto.id=prodej.auto_id;
-- vsechno

--1
select count(zakaznik.id) as 'Count', state
from zakaznik
group by state
order by Count DESC;

--2
select count(zakaznik.id) as 'Count', gender
from zakaznik
group by gender
order by Count DESC;

--3	
select count(zakaznik.id) as 'Count', state, gender
from zakaznik
group by gender,state
order by Count DESC;

--4
select *
from auto
where YEAR(rok_vyroby) BETWEEN 2000 and 2005;
select min(rok_vyroby)
from auto;

--5
select *
from auto
where rok_vyroby=(select min(rok_vyroby)
from auto);

--6
select *
from zakaznik
where id in(select prodej.zak_id
from prodej
group by prodej.zak_id
having count(prodej.id)=(select max(x.Count)
from (select count(prodej.id) as 'Count'
    from prodej
    group by zak_id) as x));

--7
select zakaznik.first_name, zakaznik.last_name, prodej.date, auto.znacka, auto.model, auto.rok_vyroby
from prodej inner join zakaznik on prodej.zak_id=zakaznik.id inner join auto on auto.id=prodej.auto_id
order by zakaznik.first_name,zakaznik.last_name,prodej.date;

--8
select *
from auto
where id in(select prodej.auto_id
from prodej
group by prodej.auto_id
having count(prodej.id)=(select max(x.Count)
from (select count(prodej.id) as 'Count'
    from prodej
    group by prodej.auto_id) as x));  --9