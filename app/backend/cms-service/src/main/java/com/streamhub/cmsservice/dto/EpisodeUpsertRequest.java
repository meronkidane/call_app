package com.streamhub.cmsservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record EpisodeUpsertRequest(
        String id,
        @NotBlank String titleId,
        @Min(0) int season,
        @Min(1) int number,
        @NotBlank String name,
        String synopsis,
        @Min(1) int durationSec) {
}
