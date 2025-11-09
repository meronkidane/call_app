# StreamHub TV Web (PWA)

A web-based TV client optimised for remote/D-pad navigation, targeting Samsung Tizen and LG webOS browsers.

## Usage

```bash
cd app/frontends/tv-web
npm install
npm run dev
```

Set `VITE_API_BASE` to point at the gateway or catalog service when connecting to a live backend.

## TODO

- Implement remote key handling abstractions for Tizen/webOS
- Add offline caching for hero rails
- Package using Samsung/Tizen CLI and LG webOS CLI for deployment
