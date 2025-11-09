#!/usr/bin/env bash
set -euo pipefail

OUTPUT_DIR="docs/openapi/generated"
SERVICES=(
  "auth-service:8081"
  "catalog-service:8082"
  "streaming-service:8083"
  "user-service:8084"
  "billing-service:8085"
  "recommendation-service:8086"
  "analytics-service:8087"
  "cms-service:8088"
  "notification-service:8089"
)

mkdir -p "${OUTPUT_DIR}"

for entry in "${SERVICES[@]}"; do
  name="${entry%%:*}"
  port="${entry##*:}"
  url="http://localhost:${port}/v3/api-docs"
  echo "[openapi] Fetching ${name} from ${url}"
  if curl --fail --silent --show-error "${url}" > "${OUTPUT_DIR}/${name}.json"; then
    echo "[openapi] Wrote ${OUTPUT_DIR}/${name}.json"
  else
    echo "[openapi] WARNING: Unable to reach ${url}. Start the service and retry." >&2
    rm -f "${OUTPUT_DIR}/${name}.json"
  fi
done
