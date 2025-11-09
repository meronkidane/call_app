# StreamHub Roku Sample Channel

This folder contains a minimal Roku SceneGraph application skeleton that consumes the StreamHub REST APIs.

## Structure

- `manifest` — Roku channel metadata
- `source/main.brs` — Entry point and simple video grid example

## Running

1. Enable developer mode on your Roku device or emulator
2. Zip the project contents and sideload via the developer web interface
3. Update the `API_BASE_URL` in `source/main.brs` to target your backend gateway

## TODO

- Implement pagination & hero rails
- Integrate Roku SceneGraph `Video` node with DRM-protected HLS playback
- Add analytics pings for play/pause/resume events
