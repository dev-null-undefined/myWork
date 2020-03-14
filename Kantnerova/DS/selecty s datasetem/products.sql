CREATE TABLE products(
   id     INTEGER PRIMARY KEY IDENTITY(1,1)
  ,dev_id INTEGER  NOT NULL FOREIGN KEY REFERENCES developers(id)
  ,name   VARCHAR(29) NOT NULL
  ,price  DECIMAL(5,2) NOT NULL
);
INSERT INTO products(dev_id,name,price) VALUES (6,'Cleaner - Bleach',13.35);
INSERT INTO products(dev_id,name,price) VALUES (2,'Soup - Campbells Asian Noodle',6.73);
INSERT INTO products(dev_id,name,price) VALUES (2,'Venison - Ground',9.30);
INSERT INTO products(dev_id,name,price) VALUES (5,'Beans - Kidney White',8.56);
INSERT INTO products(dev_id,name,price) VALUES (4,'Mini - Vol Au Vents',15.67);
INSERT INTO products(dev_id,name,price) VALUES (5,'Vinegar - Champagne',8.40);
INSERT INTO products(dev_id,name,price) VALUES (10,'General Purpose Trigger',17.80);
INSERT INTO products(dev_id,name,price) VALUES (5,'Wine - Guy Sage Touraine',9.52);
INSERT INTO products(dev_id,name,price) VALUES (4,'Caviar - Salmon',5.44);
INSERT INTO products(dev_id,name,price) VALUES (6,'Pail - 4l White, With Handle',6.42);
INSERT INTO products(dev_id,name,price) VALUES (7,'Muffin Mix - Morning Glory',6.71);
INSERT INTO products(dev_id,name,price) VALUES (7,'Tomatoes - Roma',9.54);
INSERT INTO products(dev_id,name,price) VALUES (2,'Ham - Proscuitto',15.81);
INSERT INTO products(dev_id,name,price) VALUES (10,'Ginger - Crystalized',5.15);
INSERT INTO products(dev_id,name,price) VALUES (3,'Cheese - Cheddar, Old White',5.30);
INSERT INTO products(dev_id,name,price) VALUES (6,'Grapes - Black',11.12);
INSERT INTO products(dev_id,name,price) VALUES (6,'Wine - Delicato Merlot',14.98);
INSERT INTO products(dev_id,name,price) VALUES (8,'Carrots - Mini Red Organic',13.05);
INSERT INTO products(dev_id,name,price) VALUES (7,'Wheat - Soft Kernal Of Wheat',13.72);
INSERT INTO products(dev_id,name,price) VALUES (2,'Nut - Cashews, Whole, Raw',8.97);
