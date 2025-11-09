package com.streamhub.billingservice.dto;

import java.time.Instant;

import com.streamhub.common.domain.enums.SubscriptionStatus;

public record SubscriptionResponse(String id, String userId, String planCode, SubscriptionStatus status,
                                   Instant startAt, Instant endAt, Instant renewsAt) {
}
