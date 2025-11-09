package com.streamhub.userservice.dto;

import jakarta.validation.constraints.NotBlank;

public record WatchlistRequest(@NotBlank String profileId, @NotBlank String contentId) {
}
