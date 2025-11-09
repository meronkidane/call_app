package com.streamhub.streamingservice.service;

import com.streamhub.common.api.ApiResponse;
import com.streamhub.common.streaming.CdnSigner;
import com.streamhub.common.streaming.DrmProvider;
import com.streamhub.streamingservice.dto.PlaybackStartRequest;
import com.streamhub.streamingservice.dto.PlaybackStartResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;
import java.util.Map;
import java.util.UUID;

@Service
public class PlaybackService {

    private final CdnSigner cdnSigner;
    private final DrmProvider drmProvider;

    public PlaybackService(CdnSigner cdnSigner, DrmProvider drmProvider) {
        this.cdnSigner = cdnSigner;
        this.drmProvider = drmProvider;
    }

    public Mono<ApiResponse<PlaybackStartResponse>> startPlayback(PlaybackStartRequest request) {
        var playbackToken = UUID.randomUUID().toString();
        var manifestUri = URI.create("https://cdn.local/media/" + request.contentId() + "/master.m3u8");
        var signContext = new CdnSigner.SignContext(request.contentId(), request.profileId(), request.deviceId(), request.planCode(), Duration.ofMinutes(5).toSeconds());
        return cdnSigner.sign(manifestUri, signContext)
                .zipWhen(signed -> drmProvider.issueLicense(new DrmProvider.LicenseRequest(
                        request.contentId(),
                        request.profileId(),
                        request.deviceId(),
                        request.drmType() == null ? "WIDEVINE" : request.drmType(),
                        playbackToken
                )).defaultIfEmpty(new DrmProvider.LicenseResponse("mock-license", 300, "WIDEVINE")))
                .map(tuple -> {
                    var signedStream = tuple.getT1();
                    var license = tuple.getT2();
                    var response = new PlaybackStartResponse(
                            playbackToken,
                            signedStream.signedUri().toString(),
                            signedStream.signedUri().toString().replace(".m3u8", ".mpd"),
                            "https://drm.local/licenses", Map.of(
                            "X-Playback-Token", playbackToken,
                            "X-DRM-Type", license.drmType()
                    ), license.ttlSeconds());
                    return ApiResponse.success(response);
                });
    }
}
