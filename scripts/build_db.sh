#!/bin/bash

if [ ! -d "${DB_VOLUME}" ]; then
  echo -e "${OUTPUT_COLOR}Creating db tables${NC}"

  docker build \
    -t abr/db \
    "${SCRIPTS_DIR}/postgres"

  CONTAINER_ID=$(docker run -d \
    -v "${DB_VOLUME}":/var/lib/postgresql/data \
    -e POSTGRES_USER=postgres \
    -e POSTGRES_PASSWORD=postgres \
    -e POSTGRES_DB=abr \
    abr/db)

  docker stop ${CONTAINER_ID} \
    && docker rm ${CONTAINER_ID}
fi
