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
        deploy adapters: [tomcat9(credentialsId: 'agent_linux', path: '', url: 'http://54.84.183.55:8080')], contextPath: null, war: '/home/ak/jenkins_home/workspace/a-automation/friends-9-application/target/FRIENDS9-0.0.1-SNAPSHOT.war'
      }
    }
  }
}
