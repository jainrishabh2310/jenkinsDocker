pipeline {
    agent any

    environment {
        IMAGE_NAME     = 'myapp-image'
        CONTAINER_NAME = 'jenkins'
        HOST_PORT      = '8080'
        CONT_PORT      = '8080'
    }

    stages {
    
    
    
     stage('Stop Old Container') {
            steps {
                script {
                    // Try to stop by name; if not running, just echo and continue
                    bat """
                      docker stop ${CONTAINER_NAME} || echo No running container named ${CONTAINER_NAME}
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
