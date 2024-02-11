#!/bin/bash

# Script per avviare Postgres con Docker Compose (v2)

echo Starting Postgres...


# -v $PWD/postgres:/docker-entrypoint-initdb.d
#docker run -d --hostname localhost --name orderservice -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=orderservice -v $PWD/postgres:/docker-entrypoint-initdb.d --publish 5432:5432 docker.io/postgres:latest
# docker exec orderservice psql -h localhost -U postgres CREATE DATABASE orderservice;

docker compose up -d

echo All done.

