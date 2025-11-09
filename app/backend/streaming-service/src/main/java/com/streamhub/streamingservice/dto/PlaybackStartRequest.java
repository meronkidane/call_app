package com.streamhub.streamingservice.dto;

import jakarta.validation.constraints.NotBlank;

public record PlaybackStartRequest(@NotBlank String contentId,
                                   @NotBlank String deviceId,
                                   String profileId,
                                   String planCode,
                                   String drmType) {
}
