FROM tomcat:9.0

COPY f9.war /


CMD ["catalina.sh", "run"]