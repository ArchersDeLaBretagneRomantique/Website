#!/bin/bash

API_PATH=${SCRIPT_PATH}/api

echo -e "${OUTPUT_COLOR}Building api image${NC}"
docker run --rm \
   -v ~/.m2:/root/.m2 \
   -v "${API_PATH}":/usr/src/app \
   -v /var/run/docker.sock:/var/run/docker.sock \
   -w /usr/src/app \
   maven:3.3.9-jdk-8 \
   mvn clean package docker:build -DskipTests
