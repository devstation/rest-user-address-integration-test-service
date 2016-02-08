
# RESt User/Address Integration Test (Demo Project for Article)

This project is used to setup CI/CD systems for an article about *Continuous Delivery*.

## Build & Dockerize

To build the project just run:

    mvn clean package

Afterwards the service can be dockered calling

    ./docker-build.sh

To start the container just run

    ./docker.run.sh


## Start without Docker  
  
Please create a profile (this example is dedicated to cpe's Heroku server) 
  
    mvn clean compile spring-boot:run -Dspring.profiles.active=heroku

## Start the Test

    curl http://localhost:8080/test
