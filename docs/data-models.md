# MongoDB Collections & Index Strategy

| Collection | Purpose | Key Indexes |
|------------|---------|-------------|
| `users` | Accounts with email/phone credentials | `email` (unique), `phone` (unique) |
| `profiles` | Playback personas per household | `userId` |
| `devices` | Registered devices + tokens | Compound `(userId, lastSeen)`, TTL on `lastSeen` (30 days) |
| `plans` | Subscription tiers | `code` (unique) |
| `subscriptions` | Active and historical subscriptions | Compound `(userId, status)` |
| `payments` | Provider payment receipts | `(userId, provider)` |
| `titles` | Movies and series metadata | Text index on `name`, multi-key on `genres`, `tags` |
| `episodes` | Episodic metadata | Compound `(titleId, season, number)` |
| `assets` | Rendition references, DRM flags | `parentId` |
| `watch_history` | Progress tracking | Compound `(profileId, updatedAt)` |
| `watchlist` | User favorites | Compound `(profileId, contentId)` unique |
| `events` | Playback & engagement events | TTL on `ts` (7 days) |
| `recommendations` | Cached rec lists | `profileId` |

Indexes are materialised inside `scripts/seed_dev_data.sh` to mirror production behaviour. For production deployments consider using [Spring Data MongoDB migrations](https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#mongo.migrations) or [Mongock](https://www.mongock.io/) to version-control index creation.
