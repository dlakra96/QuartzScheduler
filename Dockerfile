FROM tomcat:8.0-alpine

MAINTAINER lakradeepanshu96@gmail.com

COPY /target/*.war /usr/local/tomcat/webapps/

EXPOSE 8080

CMD ["catalina.sh","run"]


