#!/bin/bash

echo -e "${OUTPUT_COLOR}Running docker compose${NC}"
docker-compose -f "${DOCKER_COMPOSE}" up
