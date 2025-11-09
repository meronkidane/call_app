package com.streamhub.cmsservice.controller;

import com.streamhub.cmsservice.dto.*;
import com.streamhub.cmsservice.service.CmsFacade;
import com.streamhub.common.api.ApiResponse;
import com.streamhub.common.domain.Asset;
import com.streamhub.common.domain.Episode;
import com.streamhub.common.domain.Title;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/cms", produces = MediaType.APPLICATION_JSON_VALUE)
public class CmsController {

    private final CmsFacade cmsFacade;

    public CmsController(CmsFacade cmsFacade) {
        this.cmsFacade = cmsFacade;
    }

    @PostMapping(path = "/title", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ApiResponse<Title>> upsertTitle(@Valid @RequestBody TitleUpsertRequest request) {
        return cmsFacade.upsertTitle(request);
    }

    @PostMapping(path = "/episode", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ApiResponse<Episode>> upsertEpisode(@Valid @RequestBody EpisodeUpsertRequest request) {
        return cmsFacade.upsertEpisode(request);
    }

    @PostMapping(path = "/asset", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ApiResponse<Asset>> upsertAsset(@Valid @RequestBody AssetUpsertRequest request) {
        return cmsFacade.upsertAsset(request);
    }

    @PostMapping(path = "/transcode", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ApiResponse<Void>> triggerTranscode(@Valid @RequestBody TranscodeTriggerRequest request) {
        return cmsFacade.triggerTranscode(request);
    }

    @PostMapping(path = "/schedule", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ApiResponse<Void>> schedule(@Valid @RequestBody ScheduleRequest request) {
        return cmsFacade.scheduleAvailability(request);
    }
}
