pipeline {
    agent any

    stages {
        stage('Clone') {
            steps {
                git 'https://github.com/<your-username>/<repo-name>.git'
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
