CREATE DATABASE collabDB;
USE collabDB;

CREATE LOGIN seng2050-ass3
WITH PASSWORD = "Passw0rd123!";

CREATE USER seng2050-ass3
for LOGIN seng2050-ass3;

GRANT SELECT, INSERT, UPDATE, DELETE
to seng2050-ass3;

CREATE TABLE website_users	(
	username varchar(30) NOT NULL PRIMARY KEY,
	password varchar(30) NOT NULL
	
)

CREATE TABLE website_roles (
	role varchar(30) NOT NULL PRIMARY KEY
)


CREATE TABLE website_user_roles (
	username varchar(30) NOT NULL,
	role varchar(30) NOT NULL,
	
	PRIMARY KEY (username, roles),
	
	FOREIGN KEY(username) REFERENCES website_users(username),
	FOREIGN KEY(role) REFERENCES website_roles(role)

)