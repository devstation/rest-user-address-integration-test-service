#!/bin/bash

cp target/rest-uai-test.jar target/classes/docker
cd target/classes/docker
sudo docker rmi devstation/rest-uai-test:latest
sudo docker build --rm -t devstation/rest-uai-test:latest .
