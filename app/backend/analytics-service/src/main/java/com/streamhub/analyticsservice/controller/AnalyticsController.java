package com.streamhub.analyticsservice.controller;

import com.streamhub.analyticsservice.dto.AnalyticsEventRequest;
import com.streamhub.analyticsservice.dto.AnalyticsSummaryResponse;
import com.streamhub.analyticsservice.service.AnalyticsFacade;
import com.streamhub.common.api.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/analytics", produces = MediaType.APPLICATION_JSON_VALUE)
public class AnalyticsController {

    private final AnalyticsFacade analyticsFacade;

    public AnalyticsController(AnalyticsFacade analyticsFacade) {
        this.analyticsFacade = analyticsFacade;
    }

    @PostMapping(path = "/events", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ApiResponse<Void>> ingest(@Valid @RequestBody AnalyticsEventRequest request) {
        return analyticsFacade.ingest(request);
    }

    @GetMapping("/summary")
    public Mono<ApiResponse<AnalyticsSummaryResponse>> summary() {
        return analyticsFacade.summary();
    }
}
