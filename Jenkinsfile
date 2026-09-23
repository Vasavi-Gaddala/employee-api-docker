pipeline {
    agent any

    stages {

        stage('Build') {
            steps {
                echo 'Building Employee API...'

                sh 'chmod +x mvnw'

                withCredentials([
                    usernamePassword(
                        credentialsId: 'mysql-credentials',
                        usernameVariable: 'DB_USERNAME',
                        passwordVariable: 'DB_PASSWORD'
                    )
                ]) {
                    sh '''
                        export SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/employee_db
                        export SPRING_DATASOURCE_USERNAME=$DB_USERNAME
                        export SPRING_DATASOURCE_PASSWORD=$DB_PASSWORD

                        ./mvnw clean package -DskipTests
                    '''
                }
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'

                withCredentials([
                    usernamePassword(
                        credentialsId: 'mysql-credentials',
                        usernameVariable: 'DB_USERNAME',
                        passwordVariable: 'DB_PASSWORD'
                    )
                ]) {
                    sh '''
                        export SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/employee_db
                        export SPRING_DATASOURCE_USERNAME=$DB_USERNAME
                        export SPRING_DATASOURCE_PASSWORD=$DB_PASSWORD

                        ./mvnw test
                    '''
                }
            }
        }

        stage('Docker Build') {
            steps {
                echo 'Building Docker image...'

                sh '''
                    docker build -t employee-api:latest .
                '''
            }
        }

        stage('Deploy') {
            steps {
                 echo 'Deploying Employee API...'

                sh '''
                   docker compose up -d
                '''
            }
        }
    }
}