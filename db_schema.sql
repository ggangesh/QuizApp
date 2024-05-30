create schema quizdb;
use quizdb;

create table students(
    id int not null auto_increment primary key,
    firstname char(25) not null,
    lastname char(25) not null,
    username char(25) not null unique,
    password char(25) not null,
    address char(255),
    email char(255),
    mobileno bigint
);
create table questions(
    id int not null auto_increment primary key ,
    query varchar(5000) not null ,
    option1 text(5000),
    option2 text(5000),
    option3 text(5000),
    option4 text(5000),
    answerkey int not null
);

create table quizscore(
    id int not null auto_increment primary key,
    studentid int,
    score int,
    foreign key (studentid) references  students(id)
);
