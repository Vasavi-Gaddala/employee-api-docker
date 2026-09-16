# Employee API - Dockerized Application

A simple Employee Management REST API built with Java and Spring Boot, containerized using Docker and Docker Compose, and hosted on GitHub.

## Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL 8.0
- Maven
- Docker
- Docker Compose
- Git
- GitHub

## Project Architecture

Client
   |
   v
Spring Boot REST API
   |
   v
Service Layer
   |
   v
Repository Layer
   |
   v
MySQL Database

## Docker Architecture
              Docker Compose
                    |
          +---------+---------+
          |                   |
          v                   v
   Employee API          MySQL Container
   Port: 8080             Port: 3306
          |                   |
          +------ mysql ------+
             Docker Network

## API Endpoints
| Method | Endpoint          | Description        |
| ------ | ----------------- | ------------------ |
| GET    | /employees        | Get all employees  |
| POST   | /employees        | Create an employee |
| PUT    | /employees/{id}   | Update an employee |
| DELETE | /employees/{id}   | Delete an employee |

## Project Structure

employee-api/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/employee_api/
│   │   │       ├── EmployeeApiApplication.java
│   │   │       ├── controller/
│   │   │       │   └── EmployeeController.java
│   │   │       ├── Model/
│   │   │       │   └── Employee.java
│   │   │       ├── service/
│   │   │       │   └── EmployeeService.java
│   │   │       └── repository/
│   │   │           └── EmployeeRepository.java
│   │   │
│   │   └── resources/
│   │       └── application.properties
│   │
│   └── test/
│
├── Dockerfile
├── docker-compose.yml
├── pom.xml
├── mvnw
├── mvnw.cmd
├── .gitignore
├── .env
└── README.md

.env is not committed to GitHub because it contains database credentials

## Running the Application Locally
1. Build the JAR
From the project root:
    mvnw.cmd clean package
The JAR will be created inside:
    target/

## Running with Docker
1. Build the Docker Image
    docker build -t employee-api .
2. Run the Complete Application
    Docker Compose starts both:
        a. Employee API container
        b. MySQL container
    docker compose up --build
    to run in background:
    docker compose up --build -d
3. Check running Containers
    docker ps
    o/p:    employee-api-container
            employee-mysql
    The application will be avaialble at:
        http://localhost:8080

## Testing the API

GET
    curl http://localhost:8080/employees
POST
    curl -X POST http://localhost:8080/employees -H "Content-Type: application/json" -d "{\"id\":1,\"name\":\"Puneeth\",\"salary\":50000}"

PUT
    curl -X PUT http://localhost:8080/employees/1 -H "Content-Type: application/json" -d "{\"name\":\"Puneeth Updated\",\"salary\":60000}"

DELETE

    curl -X DELETE http://localhost:8080 employees/1

## Environment Variables
Database credentials are stored in a .env file.

The .env file is excluded from Git using .gitignore and is not committed to GitHub.

## CI/CD Pipeline

The planned CI/CD workflow is:

Developer
    ↓
GitHub
    ↓
Jenkins
    ↓
Maven Build
    ↓
Docker Image
    ↓
Docker Container
    ↓ok
Application

## Project Status

### Completed

- Spring Boot REST API
- CRUD operations
- MySQL integration
- Docker
- Docker Compose
- Docker Volume
- Git
- GitHub

### Next
- Jenkins CI/CD
- Automated Maven build
- Automated Docker image build
- Automated deployment