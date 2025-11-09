package com.streamhub.billingservice.repository;

import com.streamhub.common.domain.Payment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface PaymentRepository extends ReactiveMongoRepository<Payment, String> {
    Flux<Payment> findByUserId(String userId);
}
