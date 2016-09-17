#!/bin/bash

DOCKER_COMPOSE=${SCRIPT_PATH}/docker-compose.yml

echo -e "${OUTPUT_COLOR}Running docker compose${NC}"
docker-compose -f "${DOCKER_COMPOSE}" up
