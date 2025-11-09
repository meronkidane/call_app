package com.streamhub.common.domain;

import java.time.Instant;
import java.util.Map;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.streamhub.common.mongo.BaseDocument;

@Document(collection = "events")
public class EventRecord extends BaseDocument {

    private String type;

    private String userId;

    private String profileId;

    private String device;

    private String contentId;

    @Indexed(name = "idx_events_ts_ttl", expireAfterSeconds = 60 * 60 * 24 * 7)
    private Instant ts;

    private Map<String, Object> payload;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public Instant getTs() {
        return ts;
    }

    public void setTs(Instant ts) {
        this.ts = ts;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public void setPayload(Map<String, Object> payload) {
        this.payload = payload;
    }
}
