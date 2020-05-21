pipeline {
  agent { label 'lin'}
  options { timeout (time: 1, unit:'HOURS')}
  tools {
    maven 'maven'
    jdk 'java'
  }
  stages {
    stage('Build') {
      steps {
        sh '''
            echo "PATH = ${PATH}"
            echo "M2_HOME = ${M2_HOME}"
            mvn -X clean install
        '''
      }
    }
   
    stage('Deploy to Tomcat') {
      steps {
        sh '''
           sh ./deploy.sh
        '''
      }
    }
  }  
}
    
  
