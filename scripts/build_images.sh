#!/usr/bin/env bash
set -euo pipefail

SERVICES=(
  gateway
  auth-service
  catalog-service
  streaming-service
  user-service
  billing-service
  recommendation-service
  analytics-service
  cms-service
  notification-service
)

./gradlew $(printf ':app:backend:%s:bootJar ' "${SERVICES[@]}")

for service in "${SERVICES[@]}"; do
  image="streamhub/${service}:latest"
  echo "[docker] Building image ${image}"
  docker build \
    --build-arg SERVICE="${service}" \
    -t "${image}" \
    -f - . <<'DOCKER'
ARG SERVICE
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY app/backend/${SERVICE}/build/libs/*.jar service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "service.jar"]
DOCKER
  echo "[docker] Built ${image}"
done
