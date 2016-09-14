#!/bin/bash

API_PATH=${SCRIPT_PATH}/api

echo -e "${OUTPUT_COLOR}Building api image${NC}"
docker build \
  --build-arg USER_ID=$(id -u) \
  -t abr/maven \
  "${SCRIPTS_DIR}/maven"

docker run --rm \
   -v ~/.m2:/home/username/.m2 \
   -v "${API_PATH}":/usr/src/app \
   -v /var/run/docker.sock:/var/run/docker.sock \
   -w /usr/src/app \
   abr/maven \
   mvn clean package -DskipTests

docker build \
  -t abr/api \
  -f "${API_PATH}/docker/Dockerfile" \
  "${API_PATH}"
