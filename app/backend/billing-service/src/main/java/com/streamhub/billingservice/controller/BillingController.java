package com.streamhub.billingservice.controller;

import com.streamhub.billingservice.dto.BillingWebhookRequest;
import com.streamhub.billingservice.dto.PlanResponse;
import com.streamhub.billingservice.dto.SubscriptionResponse;
import com.streamhub.billingservice.dto.SubscriptionStartRequest;
import com.streamhub.billingservice.service.BillingFacade;
import com.streamhub.common.api.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/billing", produces = MediaType.APPLICATION_JSON_VALUE)
public class BillingController {

    private final BillingFacade billingFacade;

    public BillingController(BillingFacade billingFacade) {
        this.billingFacade = billingFacade;
    }

    @GetMapping("/plans")
    public Flux<PlanResponse> plans() {
        return billingFacade.plans();
    }

    @PostMapping(path = "/subscription/start", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ApiResponse<SubscriptionResponse>> start(@Valid @RequestBody SubscriptionStartRequest request) {
        return billingFacade.startSubscription(request);
    }

    @GetMapping(path = "/subscription/status/{userId}")
    public Mono<ApiResponse<SubscriptionResponse>> status(@PathVariable String userId) {
        return billingFacade.status(userId);
    }

    @PostMapping(path = "/subscription/cancel/{userId}")
    public Mono<ApiResponse<Void>> cancel(@PathVariable String userId) {
        return billingFacade.cancel(userId);
    }

    @PostMapping(path = "/webhook", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ApiResponse<Void>> webhook(@Valid @RequestBody BillingWebhookRequest request) {
        return billingFacade.handleWebhook(request);
    }
}
