pipeline{
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
            mvn -X clean package sonar:sonar
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
    stage('ws cleanup'){
      steps{
        cleanWs()
      }
    }
  }
}
