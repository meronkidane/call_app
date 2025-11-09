package com.streamhub.userservice.controller;

import com.streamhub.common.api.ApiResponse;
import com.streamhub.userservice.dto.WatchlistRequest;
import com.streamhub.userservice.dto.WatchlistResponse;
import com.streamhub.userservice.service.UserFacade;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/watchlist", produces = MediaType.APPLICATION_JSON_VALUE)
public class WatchlistController {

    private final UserFacade userFacade;

    public WatchlistController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping("/{profileId}")
    public Flux<WatchlistResponse> list(@PathVariable String profileId) {
        return userFacade.watchlist(profileId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ApiResponse<Void>> add(@Valid @RequestBody WatchlistRequest request) {
        return userFacade.addToWatchlist(request);
    }

    @DeleteMapping("/{profileId}/{contentId}")
    public Mono<ApiResponse<Void>> remove(@PathVariable String profileId, @PathVariable String contentId) {
        return userFacade.removeFromWatchlist(profileId, contentId);
    }
}
