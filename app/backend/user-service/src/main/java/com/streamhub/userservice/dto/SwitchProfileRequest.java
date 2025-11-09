package com.streamhub.userservice.dto;

import jakarta.validation.constraints.NotBlank;

public record SwitchProfileRequest(@NotBlank String userId) {
}
