FROM quay.io/wildfly/wildfly:27.0.0.Final-jdk17
RUN /opt/jboss/wildfly/bin/add-user.sh -m -u admin -p Password1! --silent
COPY target/sms.war /opt/jboss/wildfly/standalone/deployments/
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0","-bmanagement","0.0.0.0", "--debug", "*:8787"]