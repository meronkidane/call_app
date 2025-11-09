package com.streamhub.recommendationservice.service;

import com.streamhub.common.api.ApiResponse;
import com.streamhub.recommendationservice.dto.RecommendationResponse;
import com.streamhub.recommendationservice.repository.RecommendationRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class RecommendationFacade {

    private final RecommendationRepository recommendationRepository;

    public RecommendationFacade(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    public Mono<ApiResponse<RecommendationResponse>> home(String profileId) {
        return recommendationRepository.findByProfileId(profileId)
                .map(rec -> ApiResponse.success(new RecommendationResponse(rec.getProfileId(), rec.getItems())))
                .switchIfEmpty(Mono.just(ApiResponse.success(new RecommendationResponse(profileId, List.of("title-1", "title-2")))));
    }

    public Mono<ApiResponse<RecommendationResponse>> because(String profileId, String contentId) {
        return Mono.just(ApiResponse.success(new RecommendationResponse(profileId, List.of(contentId + "-similar-1", contentId + "-similar-2"))));
    }

    public Mono<ApiResponse<RecommendationResponse>> continueWatching(String profileId) {
        return Mono.just(ApiResponse.success(new RecommendationResponse(profileId, List.of("continue-1", "continue-2"))));
    }
}
