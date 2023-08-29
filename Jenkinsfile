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
								credentialsId: 'DockerCredential',
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
		stage("publish"){
			steps {
			withCredentials([string(credentialsId: 'publicip')]) {
					sshagent(credentials: ['EC2SSH']) {
							sh 'ssh -o StrictHostKeyChecking=no ubuntu@$credentialsId docker rm -f sihyun2/firstservice'
							sh 'ssh -o StrictHostKeyChecking=no ubuntu@$credentialsId docker rmi -f sihyun2/firstservice'
							sh 'ssh -o StrictHostKeyChecking=no ubuntu@$credentialsId docker pull sihyun2/firstservice'
							sh 'ssh -o StrictHostKeyChecking=no ubuntu@$credentialsId docker container run -d  --name sihyun2/firstservice -p 8080:8080 sihyun2/firstservice'
					}
				}	
			}
		}
	}
}
