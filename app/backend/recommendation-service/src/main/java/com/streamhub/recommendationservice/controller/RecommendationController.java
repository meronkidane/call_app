package com.streamhub.recommendationservice.controller;

import com.streamhub.common.api.ApiResponse;
import com.streamhub.recommendationservice.dto.RecommendationResponse;
import com.streamhub.recommendationservice.service.RecommendationFacade;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/recs", produces = MediaType.APPLICATION_JSON_VALUE)
public class RecommendationController {

    private final RecommendationFacade recommendationFacade;

    public RecommendationController(RecommendationFacade recommendationFacade) {
        this.recommendationFacade = recommendationFacade;
    }

    @GetMapping("/home/{profileId}")
    public Mono<ApiResponse<RecommendationResponse>> home(@PathVariable String profileId) {
        return recommendationFacade.home(profileId);
    }

    @GetMapping("/because/{profileId}/{contentId}")
    public Mono<ApiResponse<RecommendationResponse>> because(@PathVariable String profileId,
                                                             @PathVariable String contentId) {
        return recommendationFacade.because(profileId, contentId);
    }

    @GetMapping("/continue/{profileId}")
    public Mono<ApiResponse<RecommendationResponse>> continueWatching(@PathVariable String profileId) {
        return recommendationFacade.continueWatching(profileId);
    }
}
