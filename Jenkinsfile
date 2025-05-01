pipeline {
    agent any

    environment {
        IMAGE_NAME = 'myapp-image'
        CONTAINER_NAME = 'tender_ishizaka'
        HOST_PORT = '8080'
        CONTAINER_PORT = '8080'
    }

    stages {
        stage('Clean Old Container') {
            steps {
                script {
                    // Stop container if running
                    sh "docker ps -q -f name=${CONTAINER_NAME} | grep -q . && docker stop ${CONTAINER_NAME} || echo 'No container to stop'"
                    // Remove container if exists
                    sh "docker ps -a -q -f name=${CONTAINER_NAME} | grep -q . && docker rm ${CONTAINER_NAME} || echo 'No container to remove'"
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${IMAGE_NAME}")
                }
            }
        }

        stage('Run Docker Container') {
            steps {
                script {
                    // Run new container with the specified name and port mapping
                    docker.image("${IMAGE_NAME}")
                          .run("--name ${CONTAINER_NAME} -d -p ${HOST_PORT}:${CONTAINER_PORT}")
                }
            }
        }
    }

    post {
        success {
            echo "✅ Deployed ${IMAGE_NAME} as ${CONTAINER_NAME} on port ${HOST_PORT}"
        }
        failure {
            echo "❌ Deployment failed!"
        }
    }
}
