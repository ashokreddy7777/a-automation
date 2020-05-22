pipeline{
  agent{label 'lin'}
  options{timeout (time: 1, unit:'HOURS')}
  tools{
    maven 'maven'
    jdk 'java'
  }
  stages{
    stage('Build'){
      steps{
        sh '''
            echo "PATH = ${PATH}"
            echo "M2_HOME = ${M2_HOME}"
            mvn -X clean install
        '''     
      }
    }
    stage('Tomcat Deploy'){
      steps{
<<<<<<< HEAD
        deploy adapters: [tomcat9(credentialsId: 'agent_linux', path: '', url: 'http://54.175.110.40:8080')], contextPath: null, war: '"**/*.war"'
=======
        deploy adapters: [tomcat9(credentialsId: 'agent_linux', path: '', url: 'http://34.238.53.223:8080')], contextPath: null, war: '**/*.war'
>>>>>>> 8f507a806fedd065da310ed4ed87c78112c60a5b
      }
    }
  }
}
<<<<<<< HEAD





=======
>>>>>>> 8f507a806fedd065da310ed4ed87c78112c60a5b
