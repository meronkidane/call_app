# StreamHub Platform

StreamHub is a Netflix-style reactive streaming platform composed of modular Java Spring Boot services, mobile & TV clients, and supporting infrastructure.

## Repository Layout

- `app/backend` – Spring Boot microservices (gateway, auth, catalog, streaming, user, billing, recommendations, analytics, CMS, notifications, and common shared module)
- `app/frontends` – Client applications (React Native mobile, React Native TV, TV PWA, Roku channel, Next.js admin CMS)
- `app/infra` – Docker Compose stack, Kubernetes manifests, and Helm chart
- `docs` – Architecture diagrams, OpenAPI specifications, onboarding documentation
- `scripts` – Developer tooling (seed data, OpenAPI export, image builds)

## Local Development

1. Start backing services:
   ```bash
   make dev-up
   make seed
   ```
2. Build backend services:
   ```bash
   ./gradlew build
   ```
3. Run an individual service:
   ```bash
   make run SERVICE=app:backend:catalog-service
   ```
4. Launch a client (example for Next.js admin CMS):
   ```bash
   cd app/frontends/admin-cms
   npm install
   npm run dev
   ```

## Testing & Quality Gates

- `./gradlew test` executes unit tests across every service
- Testcontainers dependencies are declared for MongoDB, Redis, and Kafka integration tests
- Place performance scripts under `test/perf` and execute with k6 or Gatling

## Deploying to Kubernetes

1. Build container images:
   ```bash
   make docker-build
   ```
2. Install Helm chart:
   ```bash
   helm install streamhub app/infra/helm/streamhub -n streamhub --create-namespace
   ```
3. Update `values.yaml` with hostnames and credentials for managed services (Atlas, MSK, ElastiCache, etc.)

See `architecture.mmd` for the high-level platform topology.
