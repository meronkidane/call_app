package com.streamhub.common.streaming;

import reactor.core.publisher.Mono;

public interface DrmProvider {
    Mono<LicenseResponse> issueLicense(LicenseRequest request);

    record LicenseRequest(String contentId, String profileId, String deviceId, String drmType, String playbackToken) {
    }

    record LicenseResponse(String licenseKey, long ttlSeconds, String drmType) {
    }
}
