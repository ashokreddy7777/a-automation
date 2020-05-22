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
        deploy adapters: [tomcat9(credentialsId: 'agent_linux', path: '', url: 'http://54.84.183.55:8080')], contextPath: null, war: '"**/*.war"'
      }
    }
  }
}






















=======
   
    stage('Deploy to Tomcat') {
      steps {
        sh '''
           sh ./deploy.sh
        '''
      }
    }
    stage('cleanup') {
      steps {
        deleteDir();
      }
    } 
  }  
}
>>>>>>> 755c406bdd1edfe014f3e69a1d04161a6982c91d
    
  
