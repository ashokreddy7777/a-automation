pipeline {
  agent { lable 'lin'}
  options { timeout (time: 1, unit:'HOURS')}
  tools {
    maven 'maven 3.6.3'
    jdk 'jdk8'
  }
  stages {
    stage('Install Tools') {
      steps {
        sh ''''
        echo "PATH = ${PATH}"
        echo "M2_HOME = ${M2_HOME}"
        '''
      }
    stage ('Build') {
      steps {
        sh 'mvn -X install'
      }
    }  
    }
  }
}