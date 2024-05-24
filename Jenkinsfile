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
        stage('Deploy on Local K8s') {
            steps {
                withCredentials([ string(credentialsId: 'minikube-credentials', variable: 'api_token') ]) {
                    sh "kubectl --token $api_token --server https://host.docker.internal:63688  --insecure-skip-tls-verify=true apply -f ./k8s/deployment.yaml"
                    sh "kubectl --token $api_token --server https://host.docker.internal:63688  --insecure-skip-tls-verify=true apply -f ./k8s/service.yaml"
                }
            }
        }
    }
}