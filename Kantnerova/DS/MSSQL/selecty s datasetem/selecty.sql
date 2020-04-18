-- 1. vypište jména zákazníků s počtem objednávek, které mají, výpis seřaďte podle abecedy
select customers.name, count(*)
from customers
    inner join orders on orders.cust_id = customers.id
group by customers.id,customers.name
order by customers.name
-- 2. vypište jméno a zemi dodavatelů, kteří dodali výrobky za celkovou cenu (amount*price)  větší než 100 dolarů
select developers.name, developers.country
from orders
    inner join order_details on order_details.order_id=orders.id
    inner join products on products.id = order_details.product_id
    inner join developers on products.dev_id=developers.id
group by developers.id,developers.name,developers.country
having sum(amount * price)>100
-- 3. vypište nejlevnější výrobek, vypište jeho název a cenu
select products.name, products.price
from products
where products.price=(
    select min(products.price)
from products);
-- 4. vypište výrobky dodvatelů ze země 'Indonesia', vypište jmeno a zemi dodavatele a název, cenu a id výrobku
select developers.name, developers.country, products.name, products.price, products.id
from products
    inner join developers on developers.id=products.dev_id
where developers.country='Indonesia';
-- 5. zvyšte cenu výrobků dodvatelů ze země 'Indonesia' o 20%
update products 
set price=price*1.2 
from products
    inner join developers on products.dev_id=developers.id 
where developers.country='Indonesia';
-- 6. vypište názvy výrobků, které jsou od některého z dodavatelů ze zemí 'Honduras' nebo 'Indonesia'
select products.name
from products
    inner join developers on developers.id=products.dev_id
where developers.country in ('Indonesia','Honduras');
-- 7. vypište název a cenu výrobků, které si nikdo neobjednal
select products.name, products.price
from products
where products.id not in (select order_details.product_id
from order_details)