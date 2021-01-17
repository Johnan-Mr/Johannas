create table db.services(id int NOT NULL AUTO_INCREMENT primary key,
url varchar(250) NOT NULL,
name varchar(250),
creation_date timestamp NOT NULL,
last_updated timestamp,
status varchar (250),
UNIQUE(url));