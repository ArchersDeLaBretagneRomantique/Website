#!/bin/bash

echo -e "${OUTPUT_COLOR}Cleaning containers${NC}"
docker rm -f $(docker ps -aq)
