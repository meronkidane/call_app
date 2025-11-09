# CI/CD Blueprint

## GitHub Actions

- **`ci.yml`** – Build + test matrix (Java 21, Node 18). Jobs:
  - `backend` – `./gradlew build`
  - `frontends` – `npm install && npm run build` for each client path
  - `docker` – Run `make docker-build` (cache layers with `actions/cache`)

- **`security.yml`** – Dependabot triggered scanning (Snyk or OWASP Dependency Check)

## Docker Images

Use the provided `scripts/build_images.sh` locally; in CI prefer `./gradlew jibDockerBuild` once Jib is wired per service.

## Deployment Pipeline

1. **Build stage** – Produce artifacts & publish Docker images to ECR/GCR
2. **Verification** – Run smoke suite using `tests/perf/k6-smoke.js`
3. **Deploy** – Apply Helm chart (`app/infra/helm/streamhub`) via ArgoCD or Flux
4. **Post-Deploy** – Run health checks, analytics ingestion canary

Add environment-specific overrides under `app/infra/helm/streamhub/values-<env>.yaml`.
