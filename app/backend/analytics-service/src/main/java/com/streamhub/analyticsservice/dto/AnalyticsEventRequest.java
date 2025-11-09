package com.streamhub.analyticsservice.dto;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record AnalyticsEventRequest(@NotEmpty List<EventPayload> events) {

    public record EventPayload(@NotBlank String type,
                               String userId,
                               String profileId,
                               String device,
                               String contentId,
                               Instant timestamp,
                               Map<String, Object> payload) {
    }
}
