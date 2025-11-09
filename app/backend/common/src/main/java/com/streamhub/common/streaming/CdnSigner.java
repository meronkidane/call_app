package com.streamhub.common.streaming;

import java.net.URI;

import reactor.core.publisher.Mono;

public interface CdnSigner {
    Mono<SignedStream> sign(URI manifestUri, SignContext context);

    record SignContext(String contentId, String profileId, String deviceId, String planCode, long ttlSeconds) {
    }

    record SignedStream(URI signedUri, String token, long expiresInSeconds) {
    }
}
