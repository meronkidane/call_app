package com.streamhub.common.domain;

import java.time.Instant;
import java.util.List;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.streamhub.common.mongo.BaseDocument;

@Document(collection = "devices")
@CompoundIndex(name = "idx_devices_user_last_seen", def = "{ 'userId': 1, 'lastSeen': -1 }")
public class Device extends BaseDocument {

    @Indexed
    private String userId;

    private String deviceType;

    private String platform;

    @Indexed(name = "idx_devices_last_seen_ttl", expireAfterSeconds = 60 * 60 * 24 * 30)
    private Instant lastSeen;

    private List<String> tokens;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Instant getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Instant lastSeen) {
        this.lastSeen = lastSeen;
    }

    public List<String> getTokens() {
        return tokens;
    }

    public void setTokens(List<String> tokens) {
        this.tokens = tokens;
    }
}
