package com.streamhub.catalogservice.dto;

public record EpisodeResponse(String id, String titleId, int season, int number, String name, String synopsis, int durationSec) {
}
