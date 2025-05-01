pipeline {
    agent any

    environment {
        IMAGE_NAME     = 'myapp-image'
        CONTAINER_NAME = 'jenkins'           // your desired container name
        HOST_PORT      = '8080'
        CONT_PORT      = '8080'
    }

    stages {
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${IMAGE_NAME}")
                }
            }
        }

        stage('Cleanup Old Container') {
            steps {
                script {
                    // Force remove any existing container with this name
                    bat """
                      echo Cleaning up old container...
                      docker rm -f ${CONTAINER_NAME} || echo 'No old container to remove'
                    """
                }
            }
        }

        stage('Run Docker Container') {
            steps {
                script {
                    docker.image("${IMAGE_NAME}")
                          .run("--name ${CONTAINER_NAME} -d -p ${HOST_PORT}:${CONT_PORT}")
                }
            }
        }
    }

    post {
        success {
            echo "✅ ${IMAGE_NAME} deployed as ${CONTAINER_NAME} on port ${HOST_PORT}"
        }
        failure {
            echo "❌ Deployment failed!"
        }
    }
}
