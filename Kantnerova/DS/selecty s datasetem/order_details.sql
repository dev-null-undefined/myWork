CREATE TABLE order_details(
   id         INTEGER PRIMARY KEY IDENTITY(1,1)
  ,order_id   INTEGER  NOT NULL FOREIGN KEY REFERENCES orders(id)
  ,product_id INTEGER  NOT NULL FOREIGN KEY REFERENCES products(id)
  ,amount     INTEGER  NOT NULL
);
INSERT INTO order_details(order_id,product_id,amount) VALUES (15,16,4);
INSERT INTO order_details(order_id,product_id,amount) VALUES (2,4,5);
INSERT INTO order_details(order_id,product_id,amount) VALUES (15,15,1);
INSERT INTO order_details(order_id,product_id,amount) VALUES (4,7,5);
INSERT INTO order_details(order_id,product_id,amount) VALUES (3,10,5);
INSERT INTO order_details(order_id,product_id,amount) VALUES (16,7,4);
INSERT INTO order_details(order_id,product_id,amount) VALUES (12,14,4);
INSERT INTO order_details(order_id,product_id,amount) VALUES (1,17,4);
INSERT INTO order_details(order_id,product_id,amount) VALUES (2,5,5);
INSERT INTO order_details(order_id,product_id,amount) VALUES (3,15,2);
INSERT INTO order_details(order_id,product_id,amount) VALUES (1,3,4);
INSERT INTO order_details(order_id,product_id,amount) VALUES (10,17,1);
INSERT INTO order_details(order_id,product_id,amount) VALUES (5,15,4);
INSERT INTO order_details(order_id,product_id,amount) VALUES (17,14,1);
INSERT INTO order_details(order_id,product_id,amount) VALUES (19,13,1);
INSERT INTO order_details(order_id,product_id,amount) VALUES (12,12,5);
INSERT INTO order_details(order_id,product_id,amount) VALUES (4,3,4);
INSERT INTO order_details(order_id,product_id,amount) VALUES (10,7,1);
INSERT INTO order_details(order_id,product_id,amount) VALUES (10,11,3);
INSERT INTO order_details(order_id,product_id,amount) VALUES (9,3,3);
