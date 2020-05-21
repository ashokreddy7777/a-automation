pipeline {
  agent { label 'lin'}
  options { timeout (time: 1, unit:'HOURS')}
  tools {
    maven 'maven'
    jdk 'java'
  }
  stages {
    stage('Install Tools') {
      steps {
        sh ''''
        echo "PATH = ${PATH}"
        echo "M2_HOME = ${M2_HOME}"
        '''
      }
    }
    stage ('Build') {
      steps {
        sh 'mvn -X install'
      }
    }
  }  
}
    
  
