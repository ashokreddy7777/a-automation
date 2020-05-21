cp /home/ak/jenkins_home/workspace/a-automation/friends-9-application/target/FRIENDS9-0.0.1-SNAPSHOT.war /opt/tomcat/webapps -v
cd /opt/tomcat/webapps
ls -la
cd /opt/tomcat/bin
./shutdown.sh
./startup.sh

