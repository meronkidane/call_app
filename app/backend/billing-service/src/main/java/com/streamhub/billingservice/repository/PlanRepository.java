package com.streamhub.billingservice.repository;

import com.streamhub.common.domain.Plan;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface PlanRepository extends ReactiveMongoRepository<Plan, String> {
    Mono<Plan> findByCode(String code);
}
