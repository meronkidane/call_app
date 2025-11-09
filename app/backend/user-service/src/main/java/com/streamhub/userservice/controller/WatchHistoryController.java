package com.streamhub.userservice.controller;

import com.streamhub.common.api.ApiResponse;
import com.streamhub.userservice.dto.WatchHistoryResponse;
import com.streamhub.userservice.dto.WatchHistoryUpdateRequest;
import com.streamhub.userservice.service.UserFacade;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/history", produces = MediaType.APPLICATION_JSON_VALUE)
public class WatchHistoryController {

    private final UserFacade userFacade;

    public WatchHistoryController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping("/{profileId}")
    public Flux<WatchHistoryResponse> history(@PathVariable String profileId) {
        return userFacade.history(profileId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ApiResponse<WatchHistoryResponse>> update(@Valid @RequestBody WatchHistoryUpdateRequest request) {
        return userFacade.updateHistory(request);
    }
}
