pipeline {
    agent any
    stages {
        stage('Build Job') {
            steps {
                build job: 'store.job', wait: true
            }
        }

        stage('Build') { 
            steps {
                sh 'mvn clean package'
            }
        }
        
              
        stage('Build Image') {
            steps {
                script {
                    account = docker.build("alessitomas/auth:${env.BUILD_ID}", "-f Dockerfile .")
                }
            }
        }
        stage('Push Image') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhub-credential') {
                        account.push("${env.BUILD_ID}")
                        account.push("latest")
                    }
                }
            }
        }
    }
}