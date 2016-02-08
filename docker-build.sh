#!/bin/bash

cp target/rest-user-address-integration-test.jar target/classes/docker
cd target/classes/docker
sudo docker rmi devstation/rest-user-address-integration-test:latest
sudo docker build --rm -t devstation/rest-user-address-integration-test:latest .
