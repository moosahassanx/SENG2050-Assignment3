DROP TABLE appointments;
drop table responsibilities
DROP TABLE milestones;
drop table submissions	
DROP TABLE discussionsThread;
DROP TABLE discussions;
DROP TABLE group_folder_files;
DROP TABLE files;
DROP TABLE group_folders;
DROP TABLE versionFiles;
DROP TABLE user_groups;
DROP TABLE groups;
DROP TABLE website_user_roles;
DROP TABLE initial_login;
DROP TABLE user_information;
DROP TABLE website_roles;
DROP TABLE website_users;

CREATE DATABASE collabDB;
USE collabDB;

DROP LOGIN seng2050Ass3;

CREATE LOGIN seng2050Ass3
WITH PASSWORD = 'Passw0rd123!';

CREATE USER seng2050Ass3
FOR LOGIN seng2050Ass3;

ALTER ROLE db_datareader ADD member seng2050Ass3;

GRANT SELECT, INSERT, UPDATE, DELETE
TO seng2050Ass3;

CREATE TABLE website_users	(
	username VARCHAR(30) NOT NULL PRIMARY KEY,
	password VARCHAR(30) NOT NULL
)

CREATE TABLE website_roles (
	role VARCHAR(30) NOT NULL PRIMARY KEY
)

CREATE TABLE user_information	(
	username VARCHAR(30) NOT NULL PRIMARY KEY,
	studentId INT NOT NULL,
	phoneNo	INT,
	firstName varChar(30) not null,
	lastName varChar(30) not null,
)

SELECT * FROM user_information WHERE username='bitchboi3';

CREATE TABLE initial_login	(
	username VARCHAR(30) NOT NULL PRIMARY KEY,
	loginTimes INT,

	FOREIGN KEY(username) REFERENCES website_users(username)
)

CREATE TABLE website_user_roles (
	username VARCHAR(30) NOT NULL,
	role VARCHAR(30) NOT NULL,
	
	PRIMARY KEY (username, role),
	
	FOREIGN KEY(username) REFERENCES website_users(username),
	FOREIGN KEY(role) REFERENCES website_roles(role)
)

-------- TABLE FOR GROUPS ---------------------------------------
CREATE TABLE groups	(
	group_name VARCHAR(30) NOT NULL PRIMARY KEY
)
CREATE TABLE user_groups (
	username VARCHAR(30) NOT NULL,
	group_name VARCHAR(30) NOT NULL,
	
	FOREIGN KEY(username) REFERENCES website_users(username),
	FOREIGN KEY(group_name) REFERENCES groups(group_name)
)

CREATE TABLE group_folders	(
	folder_name VARCHAR(30) NOT NULL PRIMARY KEY,
	group_name VARCHAR(30) NOT NULL,
	
	FOREIGN KEY(group_name) REFERENCES groups(group_name)
)

CREATE TABLE files	(
	fileID int IDENTITY(1,1) NOT NULL PRIMARY KEY,
	binary_file VARBINARY(MAX) NOT NULL,
	file_name VARCHAR(30) NOT NULL,
	uploaded_name VARCHAR(30) NOT NULL,
	file_description VARCHAR(1000) NOT NULL,
	groupName VARCHAR(30) NOT NULL,
	
	FOREIGN KEY(uploaded_name) REFERENCES website_users(username)
)

CREATE TABLE versionFiles   (
	file_version int NOT NULL,
	file_name VARCHAR(30) NOT NULL,
	binary_file VARBINARY(MAX) NOT NULL,
	file_desc VARCHAR(1000) NOT NULL,
	uploadedName varchar(30) NOT NULL
)

CREATE TABLE group_folder_files	(
	folder_name VARCHAR(30) NOT NULL,
	fileID INT IDENTITY(1,1) NOT NULL,
	
	FOREIGN KEY(folder_name) REFERENCES group_folders(folder_name),
	FOREIGN KEY(fileID) REFERENCES files(fileID)
)

CREATE TABLE discussions(
	discussionID INT NOT NULL Identity(1,1) PRIMARY KEY, 
	title VARCHAR(100) NOT NULL, 
	username VARCHAR(30) NOT NULL, 
	description VARCHAR(1000) NOT NULL,

	FOREIGN KEY(username) REFERENCES website_users(username)
)

CREATE TABLE discussionsThread(
	threadID INT  NOT NULL Identity(1,1) PRIMARY KEY, 
	discussionID INT NOT NULL, 
	username VARCHAR(30) NOT NULL, 
	description VARCHAR(1000) NOT NULL, 

	FOREIGN KEY(discussionID) REFERENCES discussions(discussionID),
	FOREIGN KEY(username) REFERENCES website_users(username)
)

CREATE TABLE milestones(
	milestoneID INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
	milestoneTitle varChar(30) not null,
	description VARCHAR(1000) NOT NULL,
	dateDue varchar(30) not null,  
)

CREATE TABLE appointments(
	appointmentID INT not null identity(1,1) primary key,
	teacher VARCHAR(30) not null,
	username VARCHAR(30) not null,
	description VARCHAR(1000) not null,
	timeDue varchar(30) not null,
	dateDue Varchar(30) not null,

	FOREIGN KEY(username) REFERENCES website_users(username),
)

CREATE table responsibilities(
	responseID int IDENTITY(1,1) NOT NULL,
    username varchar(30) NOT NULL,
    description VARCHAR(1000),
    dateInsert varchar(30) NOT NULL,
    dateDue varchar(30) NOT NULL,
    userGroup varchar(30) NOT NULL,
    completed varchar(2) NOT NULL
)

CREATE TABLE submissions(
	submissionID int IDENTITY(1,1) not null,
	groupName varChar(30) not null,
	milestoneID int not null,
	subComment varChar(1000) not null,
	comments varChar(1000),
	mark int

	FOREIGN KEY (groupName) references groups(group_name),
	FOREIGN KEY (milestoneID) references milestones(milestoneID),
)

INSERT into website_users VALUES ('Teacher99','Covid19Bad')
INSERT INTO website_roles VALUES('student');
INSERT INTO website_roles VALUES ('teacher');

INSERT INTO website_user_roles VALUES ('Teacher99','teacher');

INSERT INTO groups VALUES('Uni Fun club');
INSERT INTO groups VALUES('Covid 19 Hate Club');
INSERT INTO groups VALUES('Roller Haters Club');
INSERT INTO groups VALUES('Flat Earth Club');
