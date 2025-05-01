pipeline {
    agent any

    environment {
        IMAGE_NAME      = 'myapp-image'
        CONTAINER_NAME  = 'tender_ishizaka'
        HOST_PORT       = '8080'
        CONT_PORT       = '8080'
    }

    stages {
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${IMAGE_NAME}")
                }
            }
        }

        stage('Stop Old Container') {
            steps {
                script {
                    // Stop the container if it is running (ignores errors if not)
                    bat """
                      for /f "tokens=*" %%i in ('docker ps -q -f name=%CONTAINER_NAME%') do (
                        echo Stopping container %%i...
                        docker stop %%i
                      )
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
