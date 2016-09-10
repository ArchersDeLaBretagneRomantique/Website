#!/bin/bash

export OUTPUT_COLOR='\033[0;36m'
export NC='\033[0m'

export SCRIPT_PATH=$(cd $(dirname $0) ; pwd -P)
export DOCKER_COMPOSE=${SCRIPT_PATH}/docker-compose.yml
export SCRIPTS_DIR=${SCRIPT_PATH}/scripts

${SCRIPTS_DIR}/clean_containers.sh
${SCRIPTS_DIR}/build_api.sh
${SCRIPTS_DIR}/build_ui.sh
${SCRIPTS_DIR}/setting_up.sh
