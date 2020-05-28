--CREATE DATABASE collabDB;
--USE DATABASE collabDB;

CREATE LOGIN seng2050Ass3
WITH PASSWORD = 'Passw0rd123!';

CREATE USER seng2050Ass3
for LOGIN seng2050Ass3;

GRANT SELECT, INSERT, UPDATE, DELETE
to seng2050Ass3;

CREATE TABLE website_users	(
	username varchar(30) NOT NULL PRIMARY KEY,
	password varchar(30) NOT NULL
)

CREATE TABLE website_roles (
	role varchar(30) NOT NULL PRIMARY KEY
)

CREATE TABLE user_information	(
	username varchar(30) NOT NULL PRIMARY KEY,
	studentId int NOT NULL,
	phoneNo	int,

)

CREATE TABLE initial_login	(
	username varchar(30) NOT NULL PRIMARY KEY,
	loginTimes int,

	FOREIGN KEY(username) REFERENCES website_users(username)
)

CREATE TABLE website_user_roles (
	username varchar(30) NOT NULL,
	role varchar(30) NOT NULL,
	
	PRIMARY KEY (username, role),
	
	FOREIGN KEY(username) REFERENCES website_users(username),
	FOREIGN KEY(role) REFERENCES website_roles(role)
)

CREATE TABLE groups	(
	group_name VARCHAR(30) NOT NULL PRIMARY KEY
)

CREATE TABLE user_groups	(
	username varchar(30) NOT NULL,
	group_name varchar(30) NOT NULL,
	
	FOREIGN KEY(username) REFERENCES website_users(username),
	FOREIGN KEY(group_name) REFERENCES groups(group_name)
	
)

CREATE TABLE group_folders	(
	folder_name varchar(30) NOT NULL PRIMARY KEY,
	group_name varchar(30) NOT NULL,
	
	FOREIGN KEY(group_name) REFERENCES groups(group_name)
)

CREATE TABLE files	(
	fileID varchar(30) NOT NULL PRIMARY KEY,
	binary_file VARBINARY(MAX) NOT NULL,
	uploaded_name varchar(30) NOT NULL,
	
	FOREIGN KEY(uploaded_name) REFERENCES website_users(username)
)

CREATE TABLE group_folder_files	(
	folder_name varchar(30) NOT NULL,
	fileID varchar(30) NOT NULL,
	
	FOREIGN KEY(folder_name) REFERENCES group_folders(folder_name),
	FOREIGN KEY(fileID) REFERENCES files(fileID)
)

CREATE TABLE discussions(
	discussionID varChar(10) NOT NULL PRIMARY KEY, 
	title varChar(100) NOT NULL, 
	username varchar(30) NOT NULL, 
	description varChar(1000) NOT NULL,

	FOREIGN KEY(username) REFERENCES website_users(username)
)

CREATE TABLE discussionsThread(
	threadID varChar(10) NOT NULL PRIMARY KEY, 
	discussionID varChar(10) NOT NULL, 
	username varChar(30) NOT NULL, 
	description varChar(1000) NOT NULL, 


	FOREIGN KEY(discussionID) REFERENCES discussions(discussionID),
	FOREIGN KEY(username) REFERENCES website_users(username)
)

INSERT INTO website_users VALUES('c3324541', 'lol')
INSERT INTO website_roles VALUES('student')
INSERT INTO website_user_roles VALUES('c3324541', 'student')
INSERT into website_users (username, password) VALUES ('Humey', '123');
INSERT INTO website_user_roles VALUES ('Humey', 'student')
