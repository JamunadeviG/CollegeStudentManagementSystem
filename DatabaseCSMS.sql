CREATE DATABASE DB;
-- Switch to the new database
USE DB;

-- Table to store student information
CREATE TABLE Students (
    id INT PRIMARY KEY, -- Unique identifier for each student
    name VARCHAR(100) NOT NULL, -- Name of the student
    department VARCHAR(100) NOT NULL -- Department the student belongs to
);

-- Table to store course information
CREATE TABLE Courses (
    code VARCHAR(10) PRIMARY KEY, -- Unique course code
    name VARCHAR(100) NOT NULL -- Name of the course
);

-- Table to store faculty information
CREATE TABLE Faculties (
    id INT PRIMARY KEY, -- Unique identifier for each faculty member
    name VARCHAR(100) NOT NULL, -- Name of the faculty member
    specialization VARCHAR(100) NOT NULL -- Faculty's specialization
);
