CREATE TABLE orders(
   id      INTEGER PRIMARY KEY IDENTITY(1,1)
  ,cust_id INTEGER  NOT NULL FOREIGN KEY REFERENCES customers(id)
  ,date    DATE  NOT NULL
);
INSERT INTO orders(cust_id,date) VALUES (2,'2017-08-14');
INSERT INTO orders(cust_id,date) VALUES (1,'2018-02-02');
INSERT INTO orders(cust_id,date) VALUES (9,'2018-02-25');
INSERT INTO orders(cust_id,date) VALUES (7,'2017-08-30');
INSERT INTO orders(cust_id,date) VALUES (5,'2017-06-02');
INSERT INTO orders(cust_id,date) VALUES (10,'2017-05-04');
INSERT INTO orders(cust_id,date) VALUES (10,'2017-08-07');
INSERT INTO orders(cust_id,date) VALUES (9,'2017-07-12');
INSERT INTO orders(cust_id,date) VALUES (7,'2018-01-18');
INSERT INTO orders(cust_id,date) VALUES (2,'2018-01-16');
INSERT INTO orders(cust_id,date) VALUES (1,'2017-12-06');
INSERT INTO orders(cust_id,date) VALUES (9,'2017-10-27');
INSERT INTO orders(cust_id,date) VALUES (3,'2017-10-08');
INSERT INTO orders(cust_id,date) VALUES (10,'2017-07-04');
INSERT INTO orders(cust_id,date) VALUES (10,'2017-07-27');
INSERT INTO orders(cust_id,date) VALUES (3,'2017-11-29');
INSERT INTO orders(cust_id,date) VALUES (6,'2018-02-17');
INSERT INTO orders(cust_id,date) VALUES (10,'2017-05-09');
INSERT INTO orders(cust_id,date) VALUES (7,'2017-03-26');
INSERT INTO orders(cust_id,date) VALUES (6,'2017-09-06');
