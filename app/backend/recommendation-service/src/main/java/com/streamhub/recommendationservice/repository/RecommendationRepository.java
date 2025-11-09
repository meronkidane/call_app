package com.streamhub.recommendationservice.repository;

import com.streamhub.common.domain.Recommendation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface RecommendationRepository extends ReactiveMongoRepository<Recommendation, String> {
    Mono<Recommendation> findByProfileId(String profileId);
}
