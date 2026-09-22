pipeline {

    agent any

    stages {

        stage('Build') {
            steps {
                echo 'Building Employee API...'
                sh 'mvnw.cmd clean package'
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                sh 'mvnw.cmd test'
            }
        }
    }
}