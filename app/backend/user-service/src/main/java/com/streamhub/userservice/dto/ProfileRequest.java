package com.streamhub.userservice.dto;

import jakarta.validation.constraints.NotBlank;

public record ProfileRequest(
        @NotBlank String userId,
        @NotBlank String name,
        String avatar,
        String maturityRating) {
}
