# Employee API - CI/CD with Jenkins, Docker and MySQL

A simple Employee Management REST API project demonstrating a real-world CI/CD workflow using **GitHub, Jenkins, Maven, Docker, Docker Compose and MySQL**.

## 🛠️ Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA / Hibernate
- MySQL 8
- Maven
- Docker
- Docker Compose
- Git
- GitHub
- Jenkins

## 📁 Project Structure

employee-api
│
├── src
│   └── main
│       ├── java
│       │   └── com.example.employee_api
│       │       ├── controller
│       │       │   └── EmployeeController.java
│       │       ├── Model
│       │       │   └── Employee.java
│       │       ├── repository
│       │       │   └── EmployeeRepository.java
│       │       ├── service
│       │       │   └── EmployeeService.java
│       │       └── EmployeeApiApplication.java
│       │
│       └── resources
│           └── application.properties
│
├── Dockerfile
├── compose.yaml
├── Jenkinsfile
├── pom.xml
├── mvnw
├── mvnw.cmd
├── .gitignore
└── README.md

.env is not committed to GitHub because it contains database credentials

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

## API Endpoints

The application provides the following REST endpoints

| Method | Endpoint          | Description        |
| ------ | ----------------- | ------------------ |
| GET    | /employees        | Get all employees  |
| POST   | /employees        | Create an employee |
| PUT    | /employees/{id}   | Update an employee |
| DELETE | /employees/{id}   | Delete an employee |

Example:
    GET http://localhost:8080/employees

## Docker
The application is packaged into a Docker image.

Dockerfile

    FROM eclipse-temurin:17-jre
    WORKDIR /app
    COPY target/employee-api-0.0.1-SNAPSHOT.jar app.jar
    EXPOSE 8080
    ENTRYPOINT ["java", "-jar", "app.jar"]

The Docker image contains the built Spring Boot JAR and runs the application on port 8080.

## Docker Compose
Docker Compose is used to run the application and MySQL together.

                 Docker Compose
                      │
             ┌────────┴──────────────┐
             ↓                       ↓
       Employee API               MySQL
       Port: 8080             employee_db
             │                        │
             └──── Docker Network ────┘

The application connects to MySQL using the Docker service name - mysql:3306
A named Docker volume is used to persist MySQL data.

## CI/CD Pipeline
The project uses Jenkins to automate the build, test and deployment process.

        Developer
            ↓
           Git
            ↓
        GitHub
            ↓
        Jenkins
            ↓
        Maven Build
            ↓
        Run Tests
            ↓
        Docker Build
            ↓
        Docker Compose
            ↓
        Deployment

## Jenkins Pipeline
The Jenkins pipeline is defined in the Jenkinsfile.
It contains four stages:

1. Build
Jenkins builds the application using the Maven Wrapper.
./mvnw clean package -DskipTests

2. Test
Jenkins runs the automated tests.
./mvnw test

3. Docker Build
Jenkins creates the Docker image.
docker build -t employee-api:latest .

4. Deploy
Jenkins deploys the application using Docker Compose.
docker compose -p employee-api up -d

## Jenkins Credentials

Database credentials are stored securely in Jenkins Credentials.
The pipeline uses:
Credential ID: mysql-credentials
The password is injected into the pipeline using Jenkins withCredentials.
Sensitive credentials are not stored in the GitHub repository.

## Docker Networking
Jenkins and the application stack communicate through the Docker network: employee-api_default
The MySQL service is accessible using the Docker DNS hostname: mysql
Therefore, the database connection uses: jdbc:mysql://mysql:3306/employee_db

## MySQL Data Persistence
MySQL uses a Docker named volume to persist database data.

MySQL Container
      ↓
Docker Volume
      ↓
Persistent Database Data

The database volume should not be deleted when recreating the MySQL container if existing data needs to be preserved.

## Security

Sensitive information is not committed to GitHub.
For example:
            .env
is excluded using .gitignore.
Database passwords are stored in Jenkins Credentials instead of being hard-coded in the Jenkinsfile.
Never commit passwords, API keys, tokens or other secrets to GitHub.

## Running the Application Locally

1. Start MySQL
docker compose -p employee-api up -d mysql
2. Start the complete application
docker compose -p employee-api up -d
3. Check running containers
docker ps
4. View application logs
docker logs employee-api-container
4. Follow application logs
docker logs -f employee-api-container

## Access the Application

After successful deployment:
http://localhost:8080/employees

## Git Workflow

After making code changes:

git add .
git commit -m "Update Employee API"
git push origin main

The updated code can then be built and deployed through Jenkins.

## CI/CD Process

The complete process is:

Developer makes code changes
          ↓
Code is committed using Git
          ↓
Code is pushed to GitHub
          ↓
Jenkins gets the latest code
          ↓
Maven builds the application
          ↓
Tests are executed
          ↓
Docker image is created
          ↓
Docker Compose deploys the application
          ↓
Application runs with MySQL

## Project Status

The complete CI/CD workflow has been successfully implemented and tested.

GitHub integration ✅
Jenkins pipeline ✅
Maven build ✅
Automated tests ✅
Docker image build ✅
Docker Compose deployment ✅
Jenkins Docker integration ✅
Docker networking ✅
Jenkins Credentials ✅
MySQL persistent storage ✅
REST API deployment ✅

## Project Objective

The main objective of this project is to understand how different DevOps tools work together in a real-world application deployment workflow:

GitHub
   +
Jenkins
   +
Maven
   +
Docker
   +
Docker Compose
   +
MySQL
   ↓
Automated CI/CD

This project provides the foundation for learning Kubernetes and more advanced CI/CD practices.
