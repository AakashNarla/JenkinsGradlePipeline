import hudson.plugins.cobertura.targets.CoverageMetric;
node {
    def app

    stage('Clone repository') {
        /* Let's make sure we have the repository cloned to our workspace */

        checkout scm
    }
    
    stage('Compile') {
 		steps {
 			sh './gradlew compileJava'
 		}
	}
	
	stage('Unit test') {
		steps {
	 		sh './gradlew test'
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
        /* This builds the actual image; synonymous to
         * docker build on the command line */

        app = docker.build("demo-image:${env.BUILD_ID}")
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