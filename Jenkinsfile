pipeline {
  environment {
    imagename = 'ashokreddy7777/docker_tomcat'
    registryCredential = 'DockerHub-ashokreddy7777'
    dockerImage = ''
  }
  agent {label 'lin'}
  stages {
    stage (Docker Build) {
      steps{
        script {
          dockerImage = docker.build imagename
        }
      }
    }
    stage (Docker Push) {
      steps{
        script{
          docker.withRegistry ('', registryCredential) {
            dockerImage.push("$BUILD_NUMBER")
            dockerImage.push('latest')
          }
        }
      }
    }
  }
}