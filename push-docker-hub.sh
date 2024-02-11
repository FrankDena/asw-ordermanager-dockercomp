#BUILD SERVICES
docker build -t  order-service:1.3 ./order-service
docker build -t  order-validation-service:1.3 ./order-validation-service
docker build -t  product-service:1.3 ./product-service
docker build -t  api-gateway:1.3 ./api-gateway

#PUSH TO DOCKER HUB
docker login

docker tag order-service:1.3 haowe/asw-ordermanager:order-service-1.3
docker push haowe/asw-ordermanager:order-service-1.3

docker tag product-service:1.3 haowe/asw-ordermanager:product-service-1.3
docker push haowe/asw-ordermanager:product-service-1.3

docker tag order-validation-service:1.3 haowe/asw-ordermanager:order-validation-service-1.3
docker push haowe/asw-ordermanager:order-validation-service-1.3

docker tag api-gateway:1.3 haowe/asw-ordermanager:api-gateway-1.3
docker push haowe/asw-ordermanager:api-gateway-1.3

echo ALL DONE!