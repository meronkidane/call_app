package com.streamhub.common.security;

import java.time.Instant;
import java.util.List;

public record JwtClaims(
        String subject,
        String userId,
        String profileId,
        String deviceId,
        List<String> roles,
        List<String> scopes,
        Instant expiresAt) {
}
