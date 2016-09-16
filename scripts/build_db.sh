#!/bin/bash

if [ ! -d "${DB_VOLUME}" ]; then
  echo -e "${OUTPUT_COLOR}Building db image${NC}"

  docker build \
    -t abr/db \
    "${SCRIPTS_DIR}/postgres"

  docker run --rm \
    -v "${DB_VOLUME}":/var/lib/postgresql/data \
    -e POSTGRES_USER=postgres \
    -e POSTGRES_PASSWORD=postgres \
    -e POSTGRES_DB=abr \
    abr/db
fi
