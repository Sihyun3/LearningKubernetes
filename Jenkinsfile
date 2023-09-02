pipeline {
	agent any
	environment{
		hash = sh (script: "git log -1 --pretty=%h", returnStdout: true).trim()
	}

	stages {
		stage("git clone") {
			steps {
				git branch: 'firstservice',
				url:'https://github.com/Sihyun3/LearningKubernetes.git'
			}
		}
		stage("Docker delete image"){
			steps{
				script {
					try{
						sh 'docker image rm $(docker images --filter=reference="sihyun2/firstservice:*" -q)'
					}catch(e){
					 	echo "삭제할 이미지가 없습니다."
					}
				}
				
			}
		}
		stage("Build") {
			steps {
                		sh "docker image build -t sihyun2/firstservice:${env.hash}  ."
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
				sh "docker push sihyun2/firstservice:${env.hash}"
			}
		}
	
		stage("publish"){
			steps {
			withCredentials([string(credentialsId: 'publicip', variable: 'credentialsId')]) {
				sh 'echo $credentialsId'
					sshagent(credentials: ['EC2SSH']) {
							sh "ssh -o StrictHostKeyChecking=no ubuntu@$credentialsId 'mkdir "testmaking"'"

							// sh "ssh -o StrictHostKeyChecking=no ubuntu@$credentialsId 'kubectl set image deployment/my-web-server1 my-web-server1=sihyun2/firstservice:${env.hash}'"
					}
				}	
			}
		}
	}
}
