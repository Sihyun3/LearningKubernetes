pipeline {
	environment {
		DOCKER_USER_ID = credentials('DOCKER_USER_ID')
		DOCKER_USER_PASSWORD = credentials('DOCKER_USER_PASSWORD')
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
				
                sh 'docker login -u $DOCKER_USER_ID -p $DOCKER_USER_PASSWORD'
			}	
		}
		stage("Docker push"){
			steps{
				sh 'docker push sihyun2/firstservice'
			}
		}
	}
}
