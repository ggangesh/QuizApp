# QuizApp

## Description:
This is Quiz application with Student and admin roles.
It has following functionalities:
1. Store 10 Java related question with answers into database
2. Each question has four options
3. System will responsible to calculate result
4. System will responsible to display result based on grade
5. Display list of student id, name with score in sorting order
6. Fetch student details by using student id.


## Requirements :
- #### Add 8.0.32+ mysql-connecter-j-{{version}}.jar in projects buildpath

## Project StartUp:
1. Import db_schema.sql into sql application
2. Run this sql file.
   So that it creates table in our db specfied in application properties
3. Change application properties such as : db.url,db.username,db.password

Then run the project as java application.

## Members & Contributions:
 - Gangesh Gudmalwar  - Admin GetStudentScoreByID , AddQuestions with display ,FileHandler
 - Pranoti Naiknaware - Admin getScoresInAscOrder
 - Prasad Ghegade     - AppDisplay
 - Giridhar           - Student Registration,login,Quiz  