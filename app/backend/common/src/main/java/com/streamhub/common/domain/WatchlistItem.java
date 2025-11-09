package com.streamhub.common.domain;

import java.time.Instant;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import com.streamhub.common.mongo.BaseDocument;

@Document(collection = "watchlist")
@CompoundIndex(name = "idx_watchlist_profile_content", def = "{ 'profileId': 1, 'contentId': 1 }", unique = true)
public class WatchlistItem extends BaseDocument {

    private String profileId;

    private String contentId;

    private Instant addedAt;

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

    public Instant getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Instant addedAt) {
        this.addedAt = addedAt;
    }
}
