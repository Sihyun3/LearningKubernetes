pipeline {
	environment {
		DOCKER_ID = credentials('DockerCredential-id')
		DOCKER_KEY = credentials('DockerCredential-key')
    }
	agent any
	stages {
		stage("git clone") {
			steps {
				git branch: 'firstservice',
				url:'https://github.com/Sihyun3/LearningKubernetes.git'
			}
		}
		stage("Build") {
			steps {
                sh 'sudo docker image -t sihyun2/firstservice build .'
			}
		}
		stage("Docker Login") {
			steps {
                sh 'docker login -u $DOCKER_ID -p $DOCKER_KEY'
			}	
		}
		stage("Docker push"){
			steps{
				sh 'docker push sihyun2/firstservice'
			}
		}
	}
}
