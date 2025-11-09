package com.streamhub.userservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record WatchHistoryUpdateRequest(
        @NotBlank String profileId,
        @NotBlank String contentId,
        @Min(0) long positionSec,
        boolean completed) {
}
