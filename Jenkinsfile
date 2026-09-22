pipeline {

    agent any

    stages {

        stage('Build') {
            steps {
                echo 'Building Employee API...'
                bat 'mvnw.cmd clean package'
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                bat 'mvnw.cmd test'
            }
        }
    }
}