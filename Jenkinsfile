pipeline {
	agent any


	stages {
		stage("git clone") {
			steps {
				git branch: 'firstservice',
				url:'https://github.com/Sihyun3/LearningKubernetes.git'
			}
		}
		stage("test"){
			steps{
				withCredentials([string(credentialsId: 'publicip', variable: 'credentialsId')]) {
				sh 'echo $credentialsId'
				}
			}
		}
		stage("publish"){
			steps {
			withCredentials([string(credentialsId: 'publicip', variable: 'credentialsId')]) {
				sh 'echo $credentialsId'
					sshagent(credentials: ['EC2SSH']) {
							sh "ssh -o StrictHostKeyChecking=no ubuntu@$credentialsId 'sudo docker rm -f sihyun2/firstservice'"
							sh "ssh -o StrictHostKeyChecking=no ubuntu@$credentialsId 'sudo docker rmi -f sihyun2/firstservice'"
							sh "ssh -o StrictHostKeyChecking=no ubuntu@$credentialsId 'sudo docker pull sihyun2/firstservice'"
							sh "ssh -o StrictHostKeyChecking=no ubuntu@$credentialsId 'sudo docker container run -d  --name sihyun2/firstservice -p 8080:8080 sihyun2/firstservice'"
					}
				}	
			}
		}
	}
}
