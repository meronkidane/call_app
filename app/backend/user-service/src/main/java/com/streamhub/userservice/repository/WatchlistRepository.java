package com.streamhub.userservice.repository;

import com.streamhub.common.domain.WatchlistItem;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WatchlistRepository extends ReactiveMongoRepository<WatchlistItem, String> {
    Flux<WatchlistItem> findByProfileId(String profileId);

    Mono<WatchlistItem> findByProfileIdAndContentId(String profileId, String contentId);
}
