#!/bin/bash

function delete_container_and_volume() {
        docker rm -f scripts_db_1
        docker volume rm scripts_db
        echo "Deleted container and volume."
}

delete_container_and_volume
docker-compose --file ./scripts/docker-compose-psql.yml up



