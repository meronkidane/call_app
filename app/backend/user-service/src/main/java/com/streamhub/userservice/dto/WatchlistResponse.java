package com.streamhub.userservice.dto;

import java.time.Instant;

public record WatchlistResponse(String id, String contentId, Instant addedAt) {
}
