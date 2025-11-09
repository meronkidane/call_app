package com.streamhub.analyticsservice.service;

import com.streamhub.analyticsservice.dto.AnalyticsEventRequest;
import com.streamhub.analyticsservice.dto.AnalyticsSummaryResponse;
import com.streamhub.analyticsservice.repository.EventRepository;
import com.streamhub.common.api.ApiResponse;
import com.streamhub.common.domain.EventRecord;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
public class AnalyticsFacade {

    private final EventRepository eventRepository;

    public AnalyticsFacade(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Mono<ApiResponse<Void>> ingest(AnalyticsEventRequest request) {
        return Flux.fromIterable(request.events())
                .map(payload -> {
                    var record = new EventRecord();
                    record.setType(payload.type());
                    record.setUserId(payload.userId());
                    record.setProfileId(payload.profileId());
                    record.setDevice(payload.device());
                    record.setContentId(payload.contentId());
                    record.setTs(payload.timestamp() == null ? Instant.now() : payload.timestamp());
                    record.setPayload(payload.payload());
                    return record;
                })
                .as(eventRepository::saveAll)
                .then(Mono.just(ApiResponse.success()));
    }

    public Mono<ApiResponse<AnalyticsSummaryResponse>> summary() {
        var summary = new AnalyticsSummaryResponse(1200, 45000, 32000, 0.72, "Sample Movie");
        return Mono.just(ApiResponse.success(summary));
    }
}
