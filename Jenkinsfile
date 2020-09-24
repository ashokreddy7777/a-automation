pipeline{
  agent any
  options{timeout (time: 1, unit:'HOURS')}
  tools{
    maven 'maven'
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
    stage('ws cleanup'){
      steps{
        cleanWs()
      }
    }
  }
}
