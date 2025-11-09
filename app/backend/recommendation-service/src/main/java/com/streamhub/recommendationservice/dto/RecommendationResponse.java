package com.streamhub.recommendationservice.dto;

import java.util.List;

public record RecommendationResponse(String profileId, List<String> items) {
}
