# StreamHub Mobile (React Native)

Mobile client targeting iOS and Android using Expo + React Navigation.

## Getting Started

```bash
cd app/frontends/mobile-react-native
npm install
npm run start
```

Set the backend API base URL with an Expo environment variable:

```bash
EXPO_PUBLIC_API_URL=http://localhost:8080 npm run start
```

## Features

- Home screen with "Continue Watching" and trending rails
- Detail view for titles with call-to-action play button
- React Query for cached API requests
- Easily extendable theme + navigation structure
