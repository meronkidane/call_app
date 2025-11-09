package com.streamhub.userservice.repository;

import com.streamhub.common.domain.WatchHistory;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WatchHistoryRepository extends ReactiveMongoRepository<WatchHistory, String> {
    Flux<WatchHistory> findByProfileIdOrderByUpdatedAtDesc(String profileId);

    Mono<WatchHistory> findByProfileIdAndContentId(String profileId, String contentId);
}
