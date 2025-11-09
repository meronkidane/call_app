# StreamHub Monorepo

A production-ready streaming platform blueprint featuring reactive Spring Boot microservices, cross-platform clients, and cloud-native infrastructure.

## Quick Start

1. **Boot infrastructure services**
   ```bash
   make dev-up
   make seed
   ```
2. **Run a backend service** (example: catalog)
   ```bash
   make run SERVICE=app:backend:catalog-service
   ```
3. **Launch the admin CMS**
   ```bash
   cd app/frontends/admin-cms
   npm install
   npm run dev
   ```
4. **OpenAPI specs** are located under `docs/openapi/`; fetch live docs with `make generate-openapi` when services are running.

## Backend Services

- `gateway`: Edge routing, rate limiting, observability hooks
- `auth-service`: Registration, login, token lifecycle, device management
- `catalog-service`: Metadata, search integrations, continue watching
- `streaming-service`: Playback tokenization, DRM abstraction, CDN signing
- `user-service`: Profiles, watchlists, watch history APIs
- `billing-service`: Plan catalog, subscription orchestration, provider webhooks
- `recommendation-service`: Hybrid recommendation rails
- `analytics-service`: Event ingestion, summary reporting, Kafka streaming
- `cms-service`: Title/asset management, scheduling, ingest triggers
- `notification-service`: Email/push hooks and queue adapters

All services share DTOs, security primitives, and Mongo document models housed in `app/backend/common`.

## Infrastructure

- `make dev-up` spins up MongoDB, Redis, Kafka, MinIO, OpenSearch, Mailhog
- Helm chart in `app/infra/helm/streamhub` deploys all services with customizable images and env vars
- Raw Kubernetes manifests in `app/infra/k8s` demonstrate namespace/config setup

## Clients

- `app/frontends/mobile-react-native`: Expo mobile app for iOS/Android
- `app/frontends/tv-react-native`: React Native TV UI skeleton for Android TV & tvOS
- `app/frontends/tv-web`: React PWA tailored for remote navigation (Tizen/webOS)
- `app/frontends/roku`: SceneGraph stub for Roku channel integration
- `app/frontends/admin-cms`: Next.js + Tailwind powered web console

## Tooling & Scripts

- `scripts/seed_dev_data.sh`: Loads sample catalog data into Mongo & media into MinIO
- `scripts/generate_openapi.sh`: Pulls live OpenAPI specs from running services
- `scripts/build_images.sh`: Builds container images for each backend service

## Documentation

- `docs/architecture.mmd`: Mermaid diagram of service topology
- `docs/openapi/*.yaml`: OpenAPI 3.1 specifications per service
- `docs/README.md`: Development, testing, and deployment playbook

---

This repository is intentionally comprehensive—use it as a launchpad for production deployments by layering in security hardening, observability wiring, CI/CD automation, and domain-specific business logic.
