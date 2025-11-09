package com.streamhub.common.messaging;

public final class KafkaTopics {
    public static final String PLAYBACK_EVENTS = "playback.events";
    public static final String SEARCH_EVENTS = "search.events";
    public static final String LIKE_EVENTS = "engagement.likes";
    public static final String ANALYTICS_EVENTS = "analytics.events";

    private KafkaTopics() {
    }
}
