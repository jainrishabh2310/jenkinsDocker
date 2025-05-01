pipeline {
    agent any

    stages {
        stage('Clone') {
            steps {
                git 'https://github.com/jainrishabh2310/jenkinsDocker.git'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build('myapp-image')
                }
            }
        }

        stage('Run Docker Container') {
            steps {
                script {
                    docker.image('myapp-image').run('-p 8080:8080')
                }
            }
        }
    }
}
