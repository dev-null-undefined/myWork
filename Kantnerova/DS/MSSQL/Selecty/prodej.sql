CREATE TABLE prodej(
id int primary key identity(1,1),
date   DATE  NOT NULL,
zak_id INT  NOT NULL foreign key references zakaznik(id),
auto_id INT  NOT NULL foreign key references auto(id)
);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2010-04-16',7,11);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2017-10-23',18,5);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2015-11-26',4,3);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2016-10-04',8,6);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2014-06-10',13,19);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2002-11-04',19,3);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2004-02-07',15,10);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2001-10-09',9,3);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2013-09-10',4,18);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2009-09-28',8,4);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2010-01-08',18,14);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2014-09-16',19,3);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2011-03-31',11,15);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2016-12-28',7,17);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2008-08-21',20,16);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2008-09-01',11,3);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2005-10-11',8,13);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2009-08-26',20,9);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2013-03-23',7,16);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2016-07-08',1,21);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2012-09-28',9,4);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2011-07-02',2,12);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2007-06-29',15,20);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2006-11-02',15,5);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2006-02-16',19,21);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2016-08-09',4,6);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2012-01-05',21,7);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2010-12-21',3,1);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2009-07-07',15,13);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2004-08-07',1,16);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2000-12-24',20,18);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2015-11-24',9,2);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2009-02-16',1,14);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2015-05-09',2,1);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2009-12-22',12,5);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2006-12-09',21,4);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2013-03-13',1,15);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2010-03-01',3,15);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2008-09-02',20,15);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2016-08-24',5,19);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2005-10-22',2,21);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2000-08-29',9,14);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2010-06-13',12,19);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2015-02-07',2,17);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2002-03-03',6,20);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2006-03-17',12,10);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2003-05-08',4,15);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2008-02-17',7,13);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2002-07-07',9,12);
INSERT INTO prodej(date,zak_id,auto_id) VALUES ('2001-07-01',18,20);
