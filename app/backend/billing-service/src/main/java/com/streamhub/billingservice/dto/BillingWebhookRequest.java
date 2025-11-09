package com.streamhub.billingservice.dto;

import java.util.Map;

import jakarta.validation.constraints.NotBlank;

public record BillingWebhookRequest(@NotBlank String provider, @NotBlank String eventType, Map<String, Object> payload) {
}
