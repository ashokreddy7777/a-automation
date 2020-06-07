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
          s3Upload (pathStyleAccessEnabled: 'true', bucket: 'a-automation', file: '/home/ak/jenkins_home/workspace/a-automation_CI_feature_ashok/target/FRIENDS9-0.0.1-SNAPSHOT.war', path: 'apps/')
        }
      }
    }
    stage('ws cleanup'){
      steps{
        cleanWs()
      }
    }
    stage('s3 download'){
      steps{
        withAWS(credentials: 'aws'){
          s3Download (pathStyleAccessEnabled: 'true', bucket: 'a-automation', file: 'apps/', path: '/home/ak/jenkins_home/workspace/a-automation_CI_feature_ashok/target', force: 'true')
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
    
  }
}