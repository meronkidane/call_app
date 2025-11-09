package com.streamhub.streamingservice.controller;

import com.streamhub.common.api.ApiResponse;
import com.streamhub.streamingservice.dto.PlaybackStartRequest;
import com.streamhub.streamingservice.dto.PlaybackStartResponse;
import com.streamhub.streamingservice.service.PlaybackService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/play", produces = MediaType.APPLICATION_JSON_VALUE)
public class PlaybackController {

    private final PlaybackService playbackService;

    public PlaybackController(PlaybackService playbackService) {
        this.playbackService = playbackService;
    }

    @PostMapping(path = "/start", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ApiResponse<PlaybackStartResponse>> start(@Valid @RequestBody PlaybackStartRequest request) {
        return playbackService.startPlayback(request);
    }
}
