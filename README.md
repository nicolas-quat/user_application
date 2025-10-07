This project is a simple Spring Boot application designed to manage users using an embedded H2 database.
It comes preloaded with 3 default users and exposes 2 REST endpoints for basic user operations.

# Installation
To run the project, clone the repository and open it in IntelliJ IDEA Ultimate.
Once opened, simply run the UserApplication class to start the application.


# Database
The application uses an H2 database.
When launched, it initializes with 3 default users.
You can access the H2 console at http://localhost:8080/h2-console. Use the following credentials to log in:
- Username: user
- Password: user
- JDBC URL: jdbc:h2:mem:user_db


# API
Two endpoints are available:
- POST /users/ - creates a new user. The request body should contain a JSON object with the user's details.
- GET /users/{id} - retrieves a user by their ID.

A Postman collection is available that covers most use cases. You can use it to send requests and verify responses.
https://nicolas-quatela-1368628.postman.co/workspace/nicolas-quatela's-Workspace~d8831743-f874-4c93-8e18-bbb02e044343/collection/48940185-80e551d4-e58c-402a-bf45-61b07d73cd79?action=share&creator=48940185
