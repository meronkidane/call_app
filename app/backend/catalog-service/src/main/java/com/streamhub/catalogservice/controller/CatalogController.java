package com.streamhub.catalogservice.controller;

import com.streamhub.catalogservice.dto.CatalogItemResponse;
import com.streamhub.catalogservice.dto.ContinueWatchingResponse;
import com.streamhub.catalogservice.dto.EpisodeResponse;
import com.streamhub.catalogservice.dto.SearchRequest;
import com.streamhub.catalogservice.service.CatalogFacade;
import com.streamhub.common.api.ApiResponse;
import com.streamhub.common.api.PaginatedResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(path = "/catalog", produces = MediaType.APPLICATION_JSON_VALUE)
public class CatalogController {

    private final CatalogFacade catalogFacade;

    public CatalogController(CatalogFacade catalogFacade) {
        this.catalogFacade = catalogFacade;
    }

    @PostMapping(path = "/search", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ApiResponse<PaginatedResponse<CatalogItemResponse>>> search(@Valid @RequestBody SearchRequest request) {
        return catalogFacade.search(request);
    }

    @GetMapping("/trending")
    public Mono<ApiResponse<List<CatalogItemResponse>>> trending() {
        return catalogFacade.trending();
    }

    @GetMapping("/title/{id}")
    public Mono<ApiResponse<CatalogItemResponse>> title(@PathVariable String id) {
        return catalogFacade.title(id);
    }

    @GetMapping("/episode/{id}")
    public Mono<ApiResponse<EpisodeResponse>> episode(@PathVariable String id) {
        return catalogFacade.episode(id);
    }

    @GetMapping("/genre/{name}")
    public Mono<ApiResponse<List<CatalogItemResponse>>> byGenre(@PathVariable String name) {
        return catalogFacade.genre(name);
    }

    @GetMapping("/continue/{profileId}")
    public Flux<ContinueWatchingResponse> continueWatching(@PathVariable String profileId) {
        return catalogFacade.continueWatching(profileId);
    }
}
