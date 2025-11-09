package com.streamhub.userservice.dto;

import java.time.Instant;

public record WatchHistoryResponse(String id, String contentId, long positionSec, boolean completed, Instant updatedAt) {
}
