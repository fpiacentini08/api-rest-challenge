#!/bin/bash

function delete_container_and_volume() {
        docker rm -f database_db_1
        docker volume rm database_db
        echo "Deleted container and volume."
}

delete_container_and_volume
docker-compose --file ./scripts/docker-compose-psql.yml up



