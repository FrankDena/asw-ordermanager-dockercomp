#!/bin/bash
###################################################CONSUL
echo Starting Consul in a Docker Container

docker run -d --hostname localhost --name asw-consul --publish 8500:8500 docker.io/hashicorp/consul
###################################################POSTGRES
echo Starting Postgres...

docker compose up -d
###################################################TOPICS
echo Creating Kafka topics for the order manager service...

KAFKA_DOCKER=$(docker ps | grep kafka | grep -v zookeeper | awk '{print $1}')

echo Creating the first channel "order-service-event-channel"...

docker exec -it $KAFKA_DOCKER kafka-topics.sh --bootstrap-server localhost:9092 --create --topic order-service-event-channel --replication-factor 1 --partitions 4

echo Creating the second channel "product-service-event-channel"...

docker exec -it $KAFKA_DOCKER kafka-topics.sh --bootstrap-server localhost:9092 --create --topic product-service-event-channel --replication-factor 1 --partitions 4

echo Creating the third channel "product-update-service-event-channel"...

docker exec -it $KAFKA_DOCKER kafka-topics.sh --bootstrap-server localhost:9092 --create --topic product-update-service-event-channel --replication-factor 1 --partitions 4
