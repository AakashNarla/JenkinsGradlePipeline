import hudson.plugins.cobertura.targets.CoverageMetric;
pipeline {
	agent any
	 triggers {
    	pollSCM('* * * * *')
  	}
    stages {
    	    	
    	stage("Compile") {
      		steps {
       			sh "./gradlew compileJava"
			}
		}
	  
    	stage("Unit test") {
      		steps {
        		sh "./gradlew test"
      		}
    	}
	
		stage("Code coverage") {
    		steps {
        		sh "./gradlew jacocoTestReport"
	          	publishHTML (target: [
	            	reportDir: 'build/reports/jacoco/test/html',
	            	reportFiles: 'index.html',
	             	reportName: "JaCoCo Report"
	          	])
	          	sh "./gradlew jacocoTestCoverageVerification"
     		}
		}

	    stage('Build image') {
	    	steps {
		    	sh "docker build -t demo-image:${env.BUILD_ID} ."
		   	}
	    }
	    
	  	stage("Docker login") {
      		steps {
      			docker.withRegistry('https://registrynxbnsf.azurecr.io', 'acr-regcred') {
	            	 sh "docker push demo-image:${env.BUILD_NUMBER}"
	            	 sh "docker push demo-image:latest"
				}
      		}
    	}
    	
	    stage('Push image') {
	        /* Finally, we'll push the image with two tags:
	         * First, the incremental build number from Jenkins
	         * Second, the 'latest' tag.
	         * Pushing multiple tags is cheap, as all the layers are reused. */
	        docker.withRegistry('https://registrynxbnsf.azurecr.io', 'acr-regcred') {
	            app.push("${env.BUILD_NUMBER}")
	            app.push("latest")
	        }
	
	    }
    
	    stage('Go to production?') {
	    	agent none
	     	steps {
	        	script {
	            	timeout(time: 1, unit: 'DAYS') {
	                	input message: 'Approve deployment?'
	             	}
	         	}
	        }
		}
	}
}