# University Management System

A REST API for managing university operations built with Spring Boot and PostgreSQL. This project demonstrates entity relationship management, custom exception handling, and file-based logging.

## Features

- Student registration and enrollment management
- Course creation with professor assignment
- Assignment creation and submission tracking
- Grade management for student submissions
- Custom exception handling for business logic
- Global exception handler with @ControllerAdvice
- File-based logging with multiple severity levels
- Graceful application shutdown endpoint

## Technologies Used

Java 17+ • Spring Boot • Spring Data JPA • PostgreSQL • Hibernate • Maven

## Database Schema

**Entities & Relationships:**
- Student ↔ Course (Many-to-Many enrollment)
- Professor → Course (One-to-Many teaching)
- Course → Assignment (One-to-Many)
- Assignment → Submission (One-to-Many)
- Student → Submission (One-to-Many)

## Getting Started

**Prerequisites:** Java JDK 17+, PostgreSQL, Maven

**Installation:**
```bash
git clone https://github.com/yourusername/university-management-system.git
cd university-management-system
```

**Configuration:** Update `application.properties` if needed
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=admin
```

**Run:**
```bash
mvn clean install
mvn spring-boot:run
```
Application runs on `http://localhost:8080` • Logs written to `logs/app.log`

## API Endpoints

**Students:** `POST /addstudent` • `GET /getstudent/id/{id}` • `GET /getstudent/name/{name}`

**Courses:** `POST /addcourse/{professorId}` • `GET /assign/{studentId}/{courseId}`

**Professors:** `POST /addprofessor` • `GET /getprofessor/id/{id}` • `GET /getprof/dept/{department}`

**Assignments:** `POST /addassignment/{courseId}` • `GET /getassignment/{id}`

**Submissions:** `POST /addsubmission/{assignmentId}/{studentId}` • `GET /getsubmission/{id}`

**System:** `POST /shutdown`

## Exception Handling

Custom exceptions (StudentNotFoundException, CourseNotFoundException, etc.) handled globally via @ControllerAdvice for consistent error responses.

## Logging

File-based logging at `logs/app.log` with INFO, WARNING, and ERROR levels. SQL query logging enabled.

## Project Structure
```
src/main/java/com/example/demo/
├── Controller/
├── Service/
├── Repository/
├── Entity/
├── Dto/
└── Exception/
```

## Testing
```bash
mvn test
```

## Author

[prerna-Katole123](https://github.com/prerna-Katole123)



*Built to demonstrate Spring Boot, JPA relationships, exception handling, and logging.*
