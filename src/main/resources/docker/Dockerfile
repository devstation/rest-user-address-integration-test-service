FROM cpelka/java:jdk8

EXPOSE 8080

WORKDIR /app

# adds the jar file to the container
ADD rest-uai-test.jar /app/rest-uai-test.jar

# add wrapper script
ADD start.sh /app/start.sh
RUN chmod 0755 /app/start.sh

# start docker command
CMD ["./start.sh"]
