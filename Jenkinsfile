pipeline {
    agent any


    stages {
        stage('Checkout Backend code') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], userRemoteConfigs: [[url: 'https://github.com/aminkhadhraoui/PROJET-S1-P1.git']]])
            }
        }
	    
	 stage('Build & test') {
            steps {
                sh 'mvn clean package'
            }
        }



        stage('Docker Build') {
      steps {
          script {
      	sh 'docker build -t amin2030/springboot_devops:latest .'
      }
      }
    }
        stage('Docker Push') {

      steps {
      	withCredentials([usernamePassword(credentialsId: 'dockerHub', passwordVariable: 'dockerHub', usernameVariable: 'amin2030')]) {
        	sh "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPassword}"
          sh 'docker push amin2030/springboot_devops:latest'
        }
      }
        }
    stage('Deploy application with monitoring') {
                        steps {
                          sh 'docker-compose up -d'

                        }
                    }

    }

}
