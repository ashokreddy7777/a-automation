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
    stage('s3 upload'){
      steps{
        withAWS(credentials: 'aws'){
          s3Upload(file: '**/**.war', bucket: 'a-automation', path: './')
        }
      }
    }
    stage('s3 download'){
      steps{
        withAWS(credentials: 'aws'){
          s3Download(file: '**/**.war', bucket: 'a-automation', path: './')
        }
      }
    }
    stage('Ansible Deployment'){
      steps{
        sh '''
           ansible-playbook /home/ak/automation/playbooks/tomcat.yml --inventory-file /home/ak/automation/inventory
        '''    
      }
    }
    stage('ws cleanup'){
      steps{
        cleanWs()
      }
    }
  }
}