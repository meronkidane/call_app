#!/usr/bin/env bash
set -euo pipefail

COMPOSE_FILE="app/infra/docker/docker-compose.yml"
MONGO_CONTAINER="streamhub-mongo"
MINIO_CONTAINER="streamhub-minio"
MEDIA_SOURCE_DIR="media/sample"

info() {
  echo "[seed] $1"
}

ensure_services() {
  if ! docker ps --format '{{.Names}}' | grep -q "${MONGO_CONTAINER}"; then
    info "Infrastructure containers are not running. Starting docker compose stack..."
    docker compose -f "${COMPOSE_FILE}" up -d mongo minio
    sleep 5
  fi
}

seed_mongo() {
  info "Seeding MongoDB sample data"
  docker compose -f "${COMPOSE_FILE}" exec -T mongo mongosh <<'MONGO'
  const db = db.getSiblingDB('streamhub');

  // Ensure indexes
  db.users.createIndex({ email: 1 }, { unique: true });
  db.users.createIndex({ phone: 1 }, { unique: true });
  db.profiles.createIndex({ userId: 1 });
  db.devices.createIndex({ userId: 1, lastSeen: -1 });
  db.devices.createIndex({ lastSeen: 1 }, { expireAfterSeconds: 60 * 60 * 24 * 30 });
  db.plans.createIndex({ code: 1 }, { unique: true });
  db.subscriptions.createIndex({ userId: 1, status: 1 });
  db.payments.createIndex({ userId: 1 });
  db.titles.createIndex({ name: "text", genres: 1, tags: 1 });
  db.episodes.createIndex({ titleId: 1, season: 1, number: 1 });
  db.assets.createIndex({ parentId: 1 });
  db.watch_history.createIndex({ profileId: 1, updatedAt: -1 });
  db.watchlist.createIndex({ profileId: 1, contentId: 1 }, { unique: true });
  db.events.createIndex({ ts: 1 }, { expireAfterSeconds: 60 * 60 * 24 * 7 });
  db.recommendations.createIndex({ profileId: 1 });

  // Seed plans
  db.plans.deleteMany({});
  db.plans.insertMany([
    { code: "BASIC", name: "Basic", price: 7.99, currency: "USD", maxDevices: 1, maxResolution: "720p", drmRequired: false },
    { code: "STANDARD", name: "Standard", price: 12.99, currency: "USD", maxDevices: 2, maxResolution: "1080p", drmRequired: true },
    { code: "PREMIUM", name: "Premium", price: 17.99, currency: "USD", maxDevices: 4, maxResolution: "4K", drmRequired: true }
  ]);

  // Seed demo users/profiles
  db.users.deleteMany({});
  const userId = db.users.insertOne({ email: "demo@streamhub.dev", phone: "+10000000000", password_hash: "demo", createdAt: new Date(), status: "ACTIVE" }).insertedId;
  db.profiles.deleteMany({});
  const profileId = db.profiles.insertOne({ userId: userId, name: "Main", avatar: "avatar-1", maturityRating: "PG13", preferences: { language: "en" } }).insertedId;

  // Seed titles and episodes
  db.titles.deleteMany({});
  const movieId = db.titles.insertOne({
    type: "MOVIE",
    name: "The Streaming Sample",
    synopsis: "A public domain adventure",
    year: 2023,
    genres: ["Adventure", "Family"],
    cast: ["Sample Actor"],
    tags: ["family", "adventure"],
    rating: 4.6,
    artwork: "/media/sample/movie/artwork.jpg",
    availabilityRegions: ["US", "CA"],
    premium: false
  }).insertedId;

  const seriesId = db.titles.insertOne({
    type: "SERIES",
    name: "Streamers",
    synopsis: "A crew of tinkers build the future of streaming",
    year: 2024,
    genres: ["Drama"],
    cast: ["Lead Actor"],
    tags: ["tech", "startup"],
    rating: 4.2,
    artwork: "/media/sample/series/artwork.jpg",
    availabilityRegions: ["US", "GB"],
    premium: true
  }).insertedId;

  db.episodes.deleteMany({});
  db.episodes.insertMany([
    { titleId: seriesId, season: 1, number: 1, name: "Pilot", synopsis: "Welcome to Streamers", durationSec: 1800 },
    { titleId: seriesId, season: 1, number: 2, name: "Scaling Pains", synopsis: "First outage", durationSec: 1820 }
  ]);

  db.assets.deleteMany({});
  db.assets.insertMany([
    { parentId: movieId, codecs: ["h264"], renditions: ["720p"], drm: "WIDEVINE", hlsUrl: "https://cdn.local/sample/movie/master.m3u8", dashUrl: "https://cdn.local/sample/movie/master.mpd", captions: ["/media/sample/movie/captions_en.vtt"], posters: ["/media/sample/movie/poster.jpg"] },
    { parentId: seriesId, codecs: ["h264"], renditions: ["1080p"], drm: "WIDEVINE", hlsUrl: "https://cdn.local/sample/series/master.m3u8", dashUrl: "https://cdn.local/sample/series/master.mpd", captions: [], posters: ["/media/sample/series/poster.jpg"] }
  ]);

  db.recommendations.deleteMany({});
  db.recommendations.insertOne({ profileId: profileId, items: [movieId, seriesId], updatedAt: new Date() });

  db.watch_history.deleteMany({});
  db.watch_history.insertOne({ profileId: profileId, contentId: movieId, positionSec: 120, completed: false, updatedAt: new Date() });

  db.watchlist.deleteMany({});
  db.watchlist.insertOne({ profileId: profileId, contentId: seriesId, addedAt: new Date() });
MONGO
}

seed_minio() {
  if [[ ! -d "${MEDIA_SOURCE_DIR}" ]]; then
    info "No sample media directory found at ${MEDIA_SOURCE_DIR}; skipping MinIO seed"
    return
  fi

  info "Uploading sample media to MinIO"
  docker compose -f "${COMPOSE_FILE}" exec -T minio mc alias set local http://localhost:9000 streamhub streamhub123 >/dev/null 2>&1 || true
  docker compose -f "${COMPOSE_FILE}" exec -T minio mc mb --ignore-existing local/media >/dev/null 2>&1
  tar -C "${MEDIA_SOURCE_DIR}" -cf - . | docker exec -i ${MINIO_CONTAINER} tar -C /tmp -xf -
  docker compose -f "${COMPOSE_FILE}" exec -T minio mc mirror --overwrite /tmp local/media >/dev/null 2>&1
  docker compose -f "${COMPOSE_FILE}" exec -T minio rm -r --force /tmp >/dev/null 2>&1 || true
}

main() {
  ensure_services
  seed_mongo
  seed_minio
  info "Seed complete"
}

main "$@"
