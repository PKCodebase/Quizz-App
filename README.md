QuizzApp

QuizzApp is a Spring Boot-based application designed to manage and conduct quizzes.
It provides REST APIs to handle questions, quizzes, and user responses, making it a robust solution for educational or entertainment purposes.

Features: 
Swagger API Documentation: Easily explore and test APIs.
Question Management: Add, update, delete, and fetch questions.
Quiz Management: Create quizzes by selecting a category and a number of random questions.
Quiz Submission: Submit responses and calculate scores.
Category-Based Filtering: Fetch questions and quizzes by category.
Database Integration: Persist data using MySQL.


Technologies Used: 
Spring Boot (v3.3.6)
Java (v17)
Spring Data JPA
MySQL
Swagger (Springdoc OpenAPI v2.2.0)
Lombok for reducing boilerplate code
Maven as the build tool


API Endpoints:


Question APIs:

GET:question/all	Get all questions
GET:question/category/{category}	Get questions by category
POST:question/add	Add a new question
PUT:question/update/{id}	Update an existing question
DELETE:question/delete/{id}	Delete a question by ID



Quiz APIs:

POST:quiz/create	Create a new quiz
GET:quiz/get/{id}	Get questions for a specific quiz
POST:quiz/submit/{id}	Submit responses for a quiz





