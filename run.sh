#!/bin/bash

export OUTPUT_COLOR='\033[0;36m'
export NC='\033[0m'

export SCRIPT_PATH=$(cd $(dirname $0) ; pwd -P)
export SCRIPTS_DIR=${SCRIPT_PATH}/scripts

export DB_VOLUME=${SCRIPT_PATH}/db
export PHOTOS_VOLUME=${SCRIPT_PATH}/photos

${SCRIPTS_DIR}/clean_containers.sh
${SCRIPTS_DIR}/build_db.sh
${SCRIPTS_DIR}/build_api.sh
${SCRIPTS_DIR}/build_ui.sh
${SCRIPTS_DIR}/startup.tart
