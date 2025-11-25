# University Management System

This project is a Spring Boot application designed to manage university-related entities such as students, courses, and lab work. It provides a RESTful API for performing CRUD operations and other interactions between these entities.

## Technologies Used

- **Java 21**
- **Spring Boot 3.5.7**
- **Spring Web**: For creating RESTful APIs.
- **Spring Data JPA**: For data persistence.
- **Spring Security**: For authentication and authorization.
- **H2 Database**: In-memory database for development and testing.
- **Flyway**: For database migrations.
- **Lombok**: To reduce boilerplate code.
- **Maven**: For project build and dependency management.
- **JUnit 5 & Mockito**: For unit and integration testing.

## API Endpoints

The application exposes the following REST endpoints:

### Authentication (`/api/auth`)
- `POST /api/auth/register`: Register a new user.
- `POST /api/auth/login`: Authenticate a user and receive a JWT token.

### Students (`/api/students`)
- `POST /`: Create a new student.
- `GET /`: Retrieve a list of all students.
- `GET /{id}`: Get a specific student by their ID.
- `GET /{id}/details`: Get detailed information about a student, including their courses.
- `GET /search`: Find a student by their email.
- `DELETE /{id}`: Delete a student.

### Courses (`/api/courses`)
- `POST /`: Create a new course.
- `GET /`: Get all courses, with an optional filter for the academic year.
- `GET /{id}`: Get a specific course by its ID.
- `GET /{id}/details`: Get detailed information about a course, including enrolled students.
- `PUT /{id}`: Update an existing course.
- `DELETE /{id}`: Delete a course.
- `POST /enroll`: Enroll a student in a course.
- `POST /unenroll`: Unenroll a student from a course.

### Lab Work (`/api/labs`)
- `POST /`: Create a new lab work assignment.
- `GET /course/{courseId}`: Get all lab work for a specific course.
- `GET /{id}`: Get a specific lab work assignment by its ID.
- `DELETE /{id}`: Delete a lab work assignment.
- `POST /submit`: Submit a lab work for a student.
- `POST /grade`: Grade a lab work submission.
- `GET /{labId}/submissions`: Get all submissions for a specific lab work.

### Exams (`/api/exams`)
- `POST /`: Create a new exam.
- `GET /course/{courseId}`: Get all exams for a specific course.
- `DELETE /{id}`: Delete an exam.
- `POST /grade`: Grade an exam for a student.

### Journal (`/api/journal`)
- `GET /student/{studentId}`: Get the academic journal (grades) for a specific student.

## How to Run

1. **Prerequisites**:
   - JDK 21 or later.
- Maven.

2. **Build the project**:
   ```bash
   mvn clean install
   ```

3. **Run the application**:
   ```bash
   mvn spring-boot:run
   ```

The application will start on the default port `8080`.
