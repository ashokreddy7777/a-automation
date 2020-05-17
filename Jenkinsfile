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
}