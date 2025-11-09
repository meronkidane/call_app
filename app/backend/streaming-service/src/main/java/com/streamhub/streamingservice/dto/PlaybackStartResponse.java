package com.streamhub.streamingservice.dto;

import java.util.Map;

public record PlaybackStartResponse(
        String playbackToken,
        String hlsUrl,
        String dashUrl,
        String licenseServerUrl,
        Map<String, String> drmHeaders,
        long expiresInSeconds) {
}
