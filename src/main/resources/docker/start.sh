#!/bin/bash

java -server -Djava.security.egd=file:/dev/./urandom -jar /app/rest-user-service.jar
