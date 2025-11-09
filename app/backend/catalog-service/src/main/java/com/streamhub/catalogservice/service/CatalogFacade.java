package com.streamhub.catalogservice.service;

import com.streamhub.catalogservice.dto.CatalogItemResponse;
import com.streamhub.catalogservice.dto.ContinueWatchingResponse;
import com.streamhub.catalogservice.dto.EpisodeResponse;
import com.streamhub.catalogservice.dto.SearchRequest;
import com.streamhub.common.api.ApiResponse;
import com.streamhub.common.api.PaginatedResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CatalogFacade {

    public Mono<ApiResponse<PaginatedResponse<CatalogItemResponse>>> search(SearchRequest request) {
        var mockItem = new CatalogItemResponse("title-1", "MOVIE", "Sample Movie", "A mock synopsis", 2024,
                List.of("Drama"), List.of("Actor A"), 4.5, false, "https://cdn.example/artwork.jpg");
        var page = PaginatedResponse.of(List.of(mockItem), 1, request.page(), request.size());
        return Mono.just(ApiResponse.success(page));
    }

    public Mono<ApiResponse<List<CatalogItemResponse>>> trending() {
        var mockItem = new CatalogItemResponse("title-1", "SERIES", "Trending Series", "", 2023,
                List.of("Sci-Fi"), List.of("Actor B"), 4.7, true, "https://cdn.example/artwork-trending.jpg");
        return Mono.just(ApiResponse.success(List.of(mockItem)));
    }

    public Mono<ApiResponse<CatalogItemResponse>> title(String id) {
        var mockItem = new CatalogItemResponse(id, "MOVIE", "Mock Title", "Description", 2022,
                List.of("Action"), List.of("Actor C"), 4.4, false, "https://cdn.example/mock.jpg");
        return Mono.just(ApiResponse.success(mockItem));
    }

    public Mono<ApiResponse<EpisodeResponse>> episode(String id) {
        var episode = new EpisodeResponse(id, "title-1", 1, 1, "Pilot", "Pilot synopsis", 3600);
        return Mono.just(ApiResponse.success(episode));
    }

    public Mono<ApiResponse<List<CatalogItemResponse>>> genre(String name) {
        var item = new CatalogItemResponse("title-genre", "MOVIE", "Genre Pick", "", 2021,
                List.of(name), List.of("Actor D"), 4.1, false, "https://cdn.example/genre.jpg");
        return Mono.just(ApiResponse.success(List.of(item)));
    }

    public Flux<ContinueWatchingResponse> continueWatching(String profileId) {
        return Flux.just(new ContinueWatchingResponse("title-1", 120, 3600, "Sample Movie", "https://cdn.example/mock.jpg"));
    }
}
