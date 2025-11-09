package com.streamhub.billingservice.dto;

import jakarta.validation.constraints.NotBlank;

public record SubscriptionStartRequest(@NotBlank String userId,
                                       @NotBlank String planCode,
                                       String paymentToken) {
}
