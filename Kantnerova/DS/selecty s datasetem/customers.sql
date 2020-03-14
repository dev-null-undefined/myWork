CREATE TABLE customers(
   id		   INTEGER PRIMARY KEY IDENTITY(1,1)
  ,name        VARCHAR(16) NOT NULL 
  ,title       VARCHAR(3) NOT NULL
  ,address     VARCHAR(25) NOT NULL
  ,state       VARCHAR(14) NOT NULL
  ,postal_code INTEGER  NOT NULL
);
INSERT INTO customers(name,title,address,state,postal_code) VALUES ('Marcile Mynett','Mr','903 Talisman Avenue','Nevada',89706);
INSERT INTO customers(name,title,address,state,postal_code) VALUES ('Ignatius Borrill','Mr','1 Blackbird Way','Kansas',66622);
INSERT INTO customers(name,title,address,state,postal_code) VALUES ('Aigneis Antecki','Ms','210 Meadow Ridge Park','Ohio',45233);
INSERT INTO customers(name,title,address,state,postal_code) VALUES ('Ewen Espie','Mr','3 Carioca Terrace','North Carolina',28815);
INSERT INTO customers(name,title,address,state,postal_code) VALUES ('Jennine Gonoude','Dr','68324 Pierstorff Junction','Illinois',61825);
INSERT INTO customers(name,title,address,state,postal_code) VALUES ('Moira Faro','Mrs','47801 Killdeer Point','Texas',75210);
INSERT INTO customers(name,title,address,state,postal_code) VALUES ('Mignonne Kupper','Mrs','5 Express Trail','Nebraska',68179);
INSERT INTO customers(name,title,address,state,postal_code) VALUES ('Darill Baert','Dr','583 Reinke Hill','Arizona',85246);
INSERT INTO customers(name,title,address,state,postal_code) VALUES ('Janetta D''Alesio','Mrs','5 Prairieview Avenue','Arizona',85754);
INSERT INTO customers(name,title,address,state,postal_code) VALUES ('Lorianna Lohan','Mrs','78055 Straubel Road','Texas',79950);
