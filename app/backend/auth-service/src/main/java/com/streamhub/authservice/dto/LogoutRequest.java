package com.streamhub.authservice.dto;

import jakarta.validation.constraints.NotBlank;

public record LogoutRequest(@NotBlank String refreshToken, @NotBlank String deviceId) {
}
