pipeline {
    agent any

    environment {
        IMAGE_NAME      = 'myapp-image'
        CONTAINER_NAME  = 'tender_ishizaka'
        HOST_PORT       = '8080'
        CONTAINER_PORT  = '8080'
    }

    stages {
        stage('Clean Old Container') {
            steps {
                script {
                    // Stop if running
                    sh """
                        if [ \$(docker ps -q -f name=${CONTAINER_NAME}) ]; then
                          docker stop ${CONTAINER_NAME}
                        else
                          echo 'No running container named ${CONTAINER_NAME}'
                        fi
                    """
                    // Remove if exists
                    sh """
                        if [ \$(docker ps -a -q -f name=${CONTAINER_NAME}) ]; then
                          docker rm ${CONTAINER_NAME}
                        else
                          echo 'No container to remove named ${CONTAINER_NAME}'
                        fi
                    """
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
                    docker.image("${IMAGE_NAME}")
                          .run("--name ${CONTAINER_NAME} -d -p ${HOST_PORT}:${CONTAINER_PORT}")
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
