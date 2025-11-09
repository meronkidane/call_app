package com.streamhub.common.domain;

import java.time.Instant;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.streamhub.common.domain.enums.SubscriptionStatus;
import com.streamhub.common.mongo.BaseDocument;

@Document(collection = "subscriptions")
@CompoundIndex(name = "idx_subscription_user_status", def = "{ 'userId': 1, 'status': 1 }")
public class Subscription extends BaseDocument {

    @Indexed
    private String userId;

    private String planCode;

    private SubscriptionStatus status;

    private Instant startAt;

    private Instant endAt;

    private Instant renewsAt;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPlanCode() {
        return planCode;
    }

    public void setPlanCode(String planCode) {
        this.planCode = planCode;
    }

    public SubscriptionStatus getStatus() {
        return status;
    }

    public void setStatus(SubscriptionStatus status) {
        this.status = status;
    }

    public Instant getStartAt() {
        return startAt;
    }

    public void setStartAt(Instant startAt) {
        this.startAt = startAt;
    }

    public Instant getEndAt() {
        return endAt;
    }

    public void setEndAt(Instant endAt) {
        this.endAt = endAt;
    }

    public Instant getRenewsAt() {
        return renewsAt;
    }

    public void setRenewsAt(Instant renewsAt) {
        this.renewsAt = renewsAt;
    }
}
