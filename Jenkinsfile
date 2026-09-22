pipeline {

    agent any

    stages {

        stage('Build') {
            steps {
                echo 'Building Employee API...'
                sh 'chmod +x mvnw'
                sh './mvnw clean package'
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                sh './mvnw test'
            }
        }
    }
}