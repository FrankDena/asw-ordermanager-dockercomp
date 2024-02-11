#!/bin/bash

# Script per avviare Kafka con Docker Compose (v2)


echo Creating Kafka topics for the order manager service...

KAFKA_DOCKER=$(docker ps | grep kafka | grep -v zookeeper | awk '{print $1}')

echo Creating the first channel "order-service-event-channel"...

docker exec -it $KAFKA_DOCKER kafka-topics.sh --bootstrap-server localhost:9092 --create --topic order-service-event-channel --replication-factor 1 --partitions 4

echo Creating the second channel "product-service-event-channel"...

docker exec -it $KAFKA_DOCKER kafka-topics.sh --bootstrap-server localhost:9092 --create --topic product-service-event-channel --replication-factor 1 --partitions 4

echo Creating the third channel "product-update-service-event-channel"...

docker exec -it $KAFKA_DOCKER kafka-topics.sh --bootstrap-server localhost:9092 --create --topic product-update-service-event-channel --replication-factor 1 --partitions 4

