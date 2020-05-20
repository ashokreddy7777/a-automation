node('Linux')
{
  stage "Docker Build"
  def maven = docker.image ('maven:latest')
  maven.pull() // make sure the latest available from Docker Hub
  maven.inside
  {
    git 'https://github.com/ashokreddy7777/a-automation.git'
    sh 'mvn -B -X clean install'
  }
  stage('Tomcat Deploy'){
    sh 'curl -T "target/FRIENDS9.0.0.1.SNAPSHOT.war" "http://ak:ashok@54.211.131.160:8080/manager/text/deploy?path=/myapp&update=true"'
  }
}