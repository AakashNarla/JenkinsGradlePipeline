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
    	
    	stage("Build") {
	    	steps {
	        	sh "./gradlew build"
	      	}
	   	}
	
		/*stage("Code coverage") {
    		steps {
        		sh "./gradlew jacocoTestReport"
	          	publishHTML (target: [
	            	reportDir: 'build/reports/jacoco/test/html',
	            	reportFiles: 'index.html',
	             	reportName: "JaCoCo Report"
	          	])
	          	sh "./gradlew jacocoTestCoverageVerification"
     		}
		}*/

	    stage('Build image') {
	    	steps {
		    	sh "docker build -t registrynxbnsf.azurecr.io/first-image:${env.BUILD_ID} ."
		   	}
	    }
	    
	  	stage("Docker Push") {
      		steps {
      			// Build and push image with Jenkins' docker-plugin
			    script {
	      			docker.withRegistry('https://registrynxbnsf.azurecr.io', 'acr-regcred') {
		            	 sh "docker push registrynxbnsf.azurecr.io/first-image:${env.BUILD_NUMBER}"
		            	 sh "docker push registrynxbnsf.azurecr.io/first-image:latest"
					}
				}
      		}
    	}
    	/*stage ('Docker Build') {
  			steps {
			    // prepare docker build context
			    sh "cp target/project.war ./tmp-docker-build-context"
			
			    // Build and push image with Jenkins' docker-plugin
			    script {
			      withDockerServer([uri: "tcp://<my-docker-socket>"]) {
			        withDockerRegistry([credentialsId: 'acr-regcred', url: "https://https://registrynxbnsf.azurecr.io/"]) {
			            // we give the image the same version as the .war package
			            def image = docker.build("<myDockerRegistry>/<myDockerProjectRepo>:${branchVersion}", "--build-arg PACKAGE_VERSION=${branchVersion} ./tmp-docker-build-context")
			            image.push()
			        }
			      }
			    }
			  }
		}*/
    
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