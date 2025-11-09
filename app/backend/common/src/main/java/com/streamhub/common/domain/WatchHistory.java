package com.streamhub.common.domain;

import java.time.Instant;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import com.streamhub.common.mongo.BaseDocument;

@Document(collection = "watch_history")
@CompoundIndex(name = "idx_watch_history_profile_updated", def = "{ 'profileId': 1, 'updatedAt': -1 }")
public class WatchHistory extends BaseDocument {

    private String profileId;

    private String contentId;

    private long positionSec;

    private boolean completed;

    private Instant updatedAt;

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public long getPositionSec() {
        return positionSec;
    }

    public void setPositionSec(long positionSec) {
        this.positionSec = positionSec;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
