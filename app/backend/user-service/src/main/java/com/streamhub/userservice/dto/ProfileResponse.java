package com.streamhub.userservice.dto;

public record ProfileResponse(String id, String userId, String name, String avatar, String maturityRating) {
}
