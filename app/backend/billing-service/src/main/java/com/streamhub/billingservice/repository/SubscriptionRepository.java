package com.streamhub.billingservice.repository;

import com.streamhub.common.domain.Subscription;
import com.streamhub.common.domain.enums.SubscriptionStatus;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SubscriptionRepository extends ReactiveMongoRepository<Subscription, String> {
    Flux<Subscription> findByUserId(String userId);

    Mono<Subscription> findByUserIdAndStatus(String userId, SubscriptionStatus status);
}
