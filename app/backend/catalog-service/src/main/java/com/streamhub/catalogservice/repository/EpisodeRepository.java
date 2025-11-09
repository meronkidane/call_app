package com.streamhub.catalogservice.repository;

import com.streamhub.common.domain.Episode;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface EpisodeRepository extends ReactiveMongoRepository<Episode, String> {
    Flux<Episode> findByTitleId(String titleId);
}
