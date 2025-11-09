package com.streamhub.billingservice.service;

import com.streamhub.billingservice.dto.BillingWebhookRequest;
import com.streamhub.billingservice.dto.PlanResponse;
import com.streamhub.billingservice.dto.SubscriptionResponse;
import com.streamhub.billingservice.dto.SubscriptionStartRequest;
import com.streamhub.billingservice.repository.PlanRepository;
import com.streamhub.billingservice.repository.SubscriptionRepository;
import com.streamhub.common.api.ApiResponse;
import com.streamhub.common.domain.Plan;
import com.streamhub.common.domain.Subscription;
import com.streamhub.common.domain.enums.SubscriptionStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
public class BillingFacade {

    private final PlanRepository planRepository;
    private final SubscriptionRepository subscriptionRepository;

    public BillingFacade(PlanRepository planRepository, SubscriptionRepository subscriptionRepository) {
        this.planRepository = planRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    public Flux<PlanResponse> plans() {
        return planRepository.findAll()
                .map(this::toPlanResponse);
    }

    public Mono<ApiResponse<SubscriptionResponse>> startSubscription(SubscriptionStartRequest request) {
        return planRepository.findByCode(request.planCode())
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Plan not found")))
                .flatMap(plan -> subscriptionRepository.findByUserIdAndStatus(request.userId(), SubscriptionStatus.ACTIVE)
                        .flatMap(existing -> {
                            existing.setPlanCode(plan.getCode());
                            existing.setRenewsAt(Instant.now().plusSeconds(30L * 24 * 3600));
                            existing.setStatus(SubscriptionStatus.ACTIVE);
                            return subscriptionRepository.save(existing);
                        })
                        .switchIfEmpty(Mono.defer(() -> {
                            var subscription = new Subscription();
                            subscription.setUserId(request.userId());
                            subscription.setPlanCode(plan.getCode());
                            subscription.setStatus(SubscriptionStatus.ACTIVE);
                            subscription.setStartAt(Instant.now());
                            subscription.setRenewsAt(Instant.now().plusSeconds(30L * 24 * 3600));
                            return subscriptionRepository.save(subscription);
                        }))
                )
                .map(saved -> ApiResponse.success(toSubscriptionResponse(saved)));
    }

    public Mono<ApiResponse<SubscriptionResponse>> status(String userId) {
        return subscriptionRepository.findByUserIdAndStatus(userId, SubscriptionStatus.ACTIVE)
                .map(subscription -> ApiResponse.success(toSubscriptionResponse(subscription)));
    }

    public Mono<ApiResponse<Void>> cancel(String userId) {
        return subscriptionRepository.findByUserIdAndStatus(userId, SubscriptionStatus.ACTIVE)
                .flatMap(subscription -> {
                    subscription.setStatus(SubscriptionStatus.CANCELLED);
                    subscription.setEndAt(Instant.now());
                    return subscriptionRepository.save(subscription);
                })
                .thenReturn(ApiResponse.success());
    }

    public Mono<ApiResponse<Void>> handleWebhook(BillingWebhookRequest request) {
        // Stub webhook handling
        return Mono.just(ApiResponse.success());
    }

    private PlanResponse toPlanResponse(Plan plan) {
        return new PlanResponse(plan.getCode(), plan.getName(), plan.getPrice(), plan.getCurrency(), plan.getMaxDevices(), plan.getMaxResolution(), plan.isDrmRequired());
    }

    private SubscriptionResponse toSubscriptionResponse(Subscription subscription) {
        return new SubscriptionResponse(subscription.getId(), subscription.getUserId(), subscription.getPlanCode(), subscription.getStatus(), subscription.getStartAt(), subscription.getEndAt(), subscription.getRenewsAt());
    }
}
