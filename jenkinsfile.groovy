node("linux") {
 stage("workspace cleanup") {
  deleteDir();
}
 
 stage("SCM checkout") {
  checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'artifacotry', url: 'https://github.com/ashokreddy7777/DevOpsDemos']]])
}

 stage("compile") {
 sh "mvn clean install"
}

stage("Static code analysis") {
  withSonarQubeEnv(credentialsId: 'sonar') {
    sh "${scannerhome}/bin/sonar-scanner -Dsonar.projectKey=friend9 -Dsonar.sources=. -Dsonar.ProjectVersion=1.1.1 -Dsonar.sourceEncoding=UTF-8"
 }
}
 timeout(time: 1, unit: 'HOURS') { 
           def qg = waitForQualityGate() 
           if (qg.status != 'OK') {
             error "Pipeline aborted due to quality gate failure: ${qg.status}"
           }
        }
 stage("Artifactory upload") {
    def SERVER_ID = 'artifactory1' 
    def server = Artifactory.server SERVER_ID
    def uploadSpec = 
    """
    {
    "files": [
        {
            "pattern": "all/target/all-(*).war",
            "target": "libs-snapshots-local/com/"
        }
      ]
    }
    """
    def buildInfo = Artifactory.newBuildInfo() 
    buildInfo.env.capture = true 
    buildInfo=server.upload(uploadSpec) 
    server.publishBuildInfo(buildInfo) 
} 

stage("CI build notification") {
  emailext body: 'CI part is completed successfully', subject: 'friends9 app build successful', to: 'rajureddy.reformer@gmail.com' 
   }
}