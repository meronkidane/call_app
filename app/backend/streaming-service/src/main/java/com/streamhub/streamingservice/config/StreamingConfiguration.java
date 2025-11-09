package com.streamhub.streamingservice.config;

import com.streamhub.common.streaming.CdnSigner;
import com.streamhub.common.streaming.DrmProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;
import java.util.UUID;

@Configuration
public class StreamingConfiguration {

    @Bean
    public CdnSigner mockCdnSigner() {
        return (manifestUri, context) -> Mono.just(new CdnSigner.SignedStream(
                URI.create(manifestUri.toString() + "?token=" + UUID.randomUUID()),
                UUID.randomUUID().toString(),
                context.ttlSeconds()
        ));
    }

    @Bean
    public DrmProvider mockDrmProvider() {
        return request -> Mono.just(new DrmProvider.LicenseResponse(
                UUID.randomUUID().toString().replace("-", ""),
                Duration.ofMinutes(5).toSeconds(),
                request.drmType()
        ));
    }
}
