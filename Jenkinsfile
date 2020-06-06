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
          s3Upload(bucket: 'a-automation', file: 'FRIENDS9-0.0.1-SNAPSHOT.war', path: '/home/ak/jenkins_home/workspace/a-automation_CI_feature_ashok/target/')
        }
      }
    }
    stage('s3 download'){
      steps{
        withAWS(credentials: 'aws'){
          s3Download(bucket: 'a-automation', file: 'FRIENDS9-0.0.1-SNAPSHOT.war', path: '/home/ak/jenkins_home/workspace/a-automation_CI_feature_ashok/target/')
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