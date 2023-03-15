#!/bin/bash

API_REST_CHALLENGE_VERSION=$(openssl rand -hex 10)
EXTERNAL_SERVICE_VERSION=$(openssl rand -hex 10)

docker build -t piacentini08/api-rest-challenge:$API_REST_CHALLENGE_VERSION .
docker push piacentini08/api-rest-challenge:$API_REST_CHALLENGE_VERSION

docker build -t piacentini08/external-service:$EXTERNAL_SERVICE_VERSION scripts/external-service/.
docker push piacentini08/external-service:$EXTERNAL_SERVICE_VERSION
