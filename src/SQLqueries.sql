/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  abhinavsenthil
 * Created: 4 Aug 2023
 */

DROP TABLE Semester;

CREATE TABLE Semester (
    Semester varchar(255),
    PRIMARY KEY (Semester)
);

--INSERT INTO Semester (Semester) VALUES ('Fall 2022');
--INSERT INTO Semester (Semester) VALUES ('Fall 2023');


DROP TABLE Course;

CREATE TABLE Course (
    Semester varchar(255),
    CourseCode varchar(255),
    Description varchar(512),
    Seats int,
    PRIMARY KEY (Semester, CourseCode)
    
);

--INSERT INTO Course(Semester, CourseCode, Description, Seats) VALUES('Fall 2022', 'COURSE-123', 'Course 123 is Awesome!', 2);
--INSERT INTO Course(Semester, CourseCode, Description, Seats) VALUES('Fall 2023', 'COURSE-456', 'Course 456 is Awesome!', 3);

DROP TABLE Student;

CREATE TABLE Student (
    StudentID varchar(255),
    FirstName varchar(255),
    LastName varchar(255),
    PRIMARY KEY (StudentID)
    
);


DROP TABLE Schedule;

CREATE TABLE Schedule (
    Semester varchar(255),
    CourseCode varchar(255),
    StudentID varchar(255),
    Status char(1),
    Timestamp_ timestamp,
    PRIMARY KEY (Semester, CourseCode, StudentID)
    

);

