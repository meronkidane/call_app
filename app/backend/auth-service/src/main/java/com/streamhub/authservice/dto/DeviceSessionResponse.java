package com.streamhub.authservice.dto;

import java.time.Instant;

public record DeviceSessionResponse(String deviceId, String deviceType, Instant lastSeen, String status) {
}
