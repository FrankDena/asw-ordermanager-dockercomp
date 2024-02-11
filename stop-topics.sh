#!/bin/bash

# Script per avviare Kafka con Docker Compose (v2)


echo Removing Kafka topics for the order service...

KAFKA_DOCKER=$(docker ps | grep kafka | grep -v zookeeper | awk '{print $1}')

docker exec -it $KAFKA_DOCKER kafka-topics.sh --bootstrap-server localhost:9092 --delete --topic order-service-event-channel
docker exec -it $KAFKA_DOCKER kafka-topics.sh --bootstrap-server localhost:9092 --delete --topic product-service-event-channel
docker exec -it $KAFKA_DOCKER kafka-topics.sh --bootstrap-server localhost:9092 --delete --topic product-update-service-event-channel

