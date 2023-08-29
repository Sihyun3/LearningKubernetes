pipeline {
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
				withCredentials([[$class: 'UsernamePasswordMultiBinding',
								credentialsId: 'DockerCredential',
								usernameVariable: 'DOCKER_USER_ID',
								passwordVariable: 'DOCKER_USER_PASSWORD'
								]]){
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
