pipeline {
	agent any
  	environment {
		hash = "${sh'echo $(git log -1 --pretty=%h")'}"	
	}

	stages {
		stage("git clone") {
			steps {
				git branch: 'firstservice',
				url:'https://github.com/Sihyun3/LearningKubernetes.git'
			}
		}
		stage("test"){
			steps{
				echo "Hash = ${env.hash}"
			}
		}
	}
}
