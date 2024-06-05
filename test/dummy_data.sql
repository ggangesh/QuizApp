use quizdb;

INSERT INTO students (id,firstname,lastname,username,password,address,email,mobileno) VALUES (1,'gangesh','gudmalwar','gangesh','12345','nanded','gangesh@gmail.com','9087654321' );
INSERT INTO students (id,firstname,lastname,username,password,address,email,mobileno) VALUES (2,'pranoti','naiknaware','pranoti','12345abc','pune','pranot$@gmail.com','9087654321' );
INSERT INTO students (id,firstname,lastname,username,password,address,email,mobileno) VALUES (3,'giridhar','bhujbal','Giridhar@79','@676760giri','mumbai','giridhar.mumbai@gmail.com','9087654321' );
INSERT INTO students (id,firstname,lastname,username,password,address,email,mobileno) VALUES (4,'prasad','ghegade','prasad67','A0AA@78','pune','prasad2019@gmail.com','9355654321' );

INSERT INTO questions(query, option1, option2, option3, option4, answerkey) VALUES ('What is sql', 'It is strucrteed query lang', 'It is like HTML', 'studpid language','Nothing',1);
INSERT INTO questions(query, option1, option2, option3, option4, answerkey) VALUES ('Which city is maharashtra ?' ,'Bengluru' ,'Delhi' ,'Nagpur','Kolkata',3);
INSERT INTO questions(query, option1, option2, option3, option4, answerkey) VALUES ('Which is not a season?','Mansoon' ,'Hot' ,'Summer','Winteer',2);

INSERT INTO quizscore(id, studentid, score) values (1,1,2);
INSERT INTO quizscore(id, studentid, score) values (1,3,3);
