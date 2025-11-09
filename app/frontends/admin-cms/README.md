# StreamHub Admin CMS (Next.js)

Headless admin console for managing catalog metadata, assets, schedules, and editorial rails.

## Development

```bash
cd app/frontends/admin-cms
npm install
npm run dev
```

Set `NEXT_PUBLIC_API_BASE` to point at the gateway or catalog endpoints when running against a live backend.

## Key Features

- React Query data fetching with optimistic UI
- Tailwind UI primitives ready for shadcn/ui integration
- Modular navigation structure for titles, assets, schedules, and analytics
- Placeholder tables demonstrate layout and theming
