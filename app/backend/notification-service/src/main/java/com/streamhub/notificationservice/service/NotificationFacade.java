package com.streamhub.notificationservice.service;

import com.streamhub.common.api.ApiResponse;
import com.streamhub.notificationservice.dto.NotificationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class NotificationFacade {

    private static final Logger log = LoggerFactory.getLogger(NotificationFacade.class);

    public Mono<ApiResponse<Void>> send(NotificationRequest request) {
        log.info("Sending {} notification to {} with template {}", request.channel(), request.recipient(), request.template());
        return Mono.just(ApiResponse.success());
    }
}
