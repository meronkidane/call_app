package com.streamhub.authservice.dto;

public record TokenResponse(String accessToken, long expiresIn, String refreshToken, long refreshExpiresIn) {
}
