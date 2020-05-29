pipeline{
  agent{label 'lin'}
  options{timeout (time: 1, unit:'HOURS')}
  tools{
    maven 'maven'
    jdk 'java'
  }
  stages{
    stage('Build && SonarQube analysis'){
      steps{
        withSonarQubeEnv ('sonarqube'){
        sh '''
            echo "PATH = ${PATH}"
            echo "M2_HOME = ${M2_HOME}"
            mvn -X package sonar:sonar
        '''     
        } 
      }
    }
    stage('Quality Gate'){
      steps{
        timeout(time: 1, unit: 'HOURS') {
                    waitForQualityGate abortPipeline: true
        }
      }
    }
    stage('Deploy to tomcat'){
      steps{
        sh '''
           cp **/*.war /opt/tomcat/webapps/
           /opt/tomcat/bin/startup.sh
        '''    
      }
    }
    stage('upload to s3'){
      steps{
        s3Upload consoleLogLevel: 'INFO', 
        dontSetBuildResultOnFailure: true, 
        dontWaitForConcurrentBuildCompletion: false, 
        entries: [[bucket: 'a-automation', 
                   excludedFile: '', 
                   flatten: false, 
                   gzipFiles: false, 
                   keepForever: false, 
                   managedArtifacts: true, 
                   noUploadOnFailure: true, 
                   selectedRegion: 'us-east-1', 
                   showDirectlyInBrowser: false, 
                   sourceFile: '**/*.war', 
                   storageClass: 'STANDARD', 
                   uploadFromSlave: true, 
                   useServerSideEncryption: false]], 
          pluginFailureResultConstraint: 'FAILURE', 
          profileName: 's3', 
          userMetadata: []
      }
    }
    stage('ws cleanup'){
      steps{
        cleanWs()
      }
    }
    stage('Email'){
      steps{
        mail bcc: '', body: 'This email is regarding Jenkins pipeline status.', cc: '', from: '', replyTo: '', subject: 'Jenkins', to: 'ashokvardhanreddy96@gmail.com'
      }
    }
  }
}