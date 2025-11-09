SHELL := /bin/bash
COMPOSE_FILE := app/infra/docker/docker-compose.yml
GRADLE := ./gradlew

.PHONY: dev-up dev-down dev-logs dev-reset seed test run lint format generate-openapi docker-build

dev-up:
	@docker compose -f $(COMPOSE_FILE) up -d

dev-down:
	@docker compose -f $(COMPOSE_FILE) down

dev-logs:
	@docker compose -f $(COMPOSE_FILE) logs -f

dev-reset:
	@docker compose -f $(COMPOSE_FILE) down -v
	@docker compose -f $(COMPOSE_FILE) up -d

seed:
	@bash scripts/seed_dev_data.sh

run:
	@if [ -z "$(SERVICE)" ]; then echo "Usage: make run SERVICE=auth-service"; exit 1; fi
	@$(GRADLE) :app:backend:$(SERVICE):bootRun

test:
	@$(GRADLE) test

lint:
	@$(GRADLE) check

generate-openapi:
	@bash scripts/generate_openapi.sh

docker-build:
	@bash scripts/build_images.sh
