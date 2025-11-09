package com.streamhub.common.messaging;

import reactor.core.publisher.Mono;

public interface EventPublisher<T> {
    Mono<Void> publish(T payload);
}
