package com.streamhub.catalogservice.dto;

public record ContinueWatchingResponse(String contentId, long positionSec, long durationSec, String title, String artworkUrl) {
}
