#!/bin/bash

UI_PATH=${SCRIPT_PATH}/ui

echo -e "${OUTPUT_COLOR}Building ui image${NC}"
docker build \
  --build-arg USER_ID=$(id -u) \
  -t abr/node \
  "${SCRIPTS_DIR}/node"

docker run --rm \
  -v "${UI_PATH}":/usr/src/app/ \
  -e NODE_ENV=development \
  -w /usr/src/app/ \
  abr/node \
  npm install

docker run --rm \
  -v "${UI_PATH}":/usr/src/app/ \
  -e NODE_ENV=production \
  -w /usr/src/app/ \
  abr/node \
  npm run build

docker build \
  -f "${UI_PATH}"/docker/Dockerfile \
  -t abr/ui \
  "${UI_PATH}"
