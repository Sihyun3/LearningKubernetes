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
                sh 'docker image build -t sihyun2/firstservice  .'
			}
		}
		stage("Docker Login") {
			steps {
					withCredentials([[$class: 'UsernamePasswordMultiBinding',
								credentialsId: 'docker_credentials',
								usernameVariable: 'DOCKER_USER_ID',
								passwordVariable: 'DOCKER_USER_PASSWORD'
								]]){
                			sh 'docker login -u $DOCKER_USER_ID -p $DOCKER_USER_PASSWORD'
						}
			}	
		}
		stage("Docker push"){
			steps{
			
				sh 'docker push sihyun2/firstservice'
				
			}
		}
	}
}
