package com.streamhub.cmsservice.service;

import com.streamhub.cmsservice.dto.*;
import com.streamhub.cmsservice.repository.AssetRepository;
import com.streamhub.cmsservice.repository.EpisodeRepository;
import com.streamhub.cmsservice.repository.TitleRepository;
import com.streamhub.common.api.ApiResponse;
import com.streamhub.common.domain.Asset;
import com.streamhub.common.domain.Episode;
import com.streamhub.common.domain.Title;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
public class CmsFacade {

    private final TitleRepository titleRepository;
    private final EpisodeRepository episodeRepository;
    private final AssetRepository assetRepository;

    public CmsFacade(TitleRepository titleRepository,
                     EpisodeRepository episodeRepository,
                     AssetRepository assetRepository) {
        this.titleRepository = titleRepository;
        this.episodeRepository = episodeRepository;
        this.assetRepository = assetRepository;
    }

    public Mono<ApiResponse<Title>> upsertTitle(TitleUpsertRequest request) {
        Mono<Title> titleMono = request.id() == null ? Mono.just(new Title()) : titleRepository.findById(request.id()).defaultIfEmpty(new Title());
        return titleMono.flatMap(title -> {
            title.setType(request.type());
            title.setName(request.name());
            title.setSynopsis(request.synopsis());
            title.setYear(request.year());
            title.setGenres(request.genres());
            title.setCast(request.cast());
            title.setTags(request.tags());
            title.setRating(request.rating());
            title.setArtwork(request.artwork());
            title.setAvailabilityRegions(request.availabilityRegions());
            title.setPremium(request.premium());
            return titleRepository.save(title);
        }).map(ApiResponse::success);
    }

    public Mono<ApiResponse<Episode>> upsertEpisode(EpisodeUpsertRequest request) {
        Mono<Episode> episodeMono = request.id() == null ? Mono.just(new Episode()) : episodeRepository.findById(request.id()).defaultIfEmpty(new Episode());
        return episodeMono.flatMap(episode -> {
            episode.setTitleId(request.titleId());
            episode.setSeason(request.season());
            episode.setNumber(request.number());
            episode.setName(request.name());
            episode.setSynopsis(request.synopsis());
            episode.setDurationSec(request.durationSec());
            return episodeRepository.save(episode);
        }).map(ApiResponse::success);
    }

    public Mono<ApiResponse<Asset>> upsertAsset(AssetUpsertRequest request) {
        Mono<Asset> assetMono = request.id() == null ? Mono.just(new Asset()) : assetRepository.findById(request.id()).defaultIfEmpty(new Asset());
        return assetMono.flatMap(asset -> {
            asset.setParentId(request.parentId());
            asset.setCodecs(request.codecs());
            asset.setRenditions(request.renditions());
            asset.setDrm(request.drm());
            asset.setHlsUrl(request.hlsUrl());
            asset.setDashUrl(request.dashUrl());
            asset.setCaptions(request.captions());
            asset.setPosters(request.posters());
            return assetRepository.save(asset);
        }).map(ApiResponse::success);
    }

    public Mono<ApiResponse<Void>> triggerTranscode(TranscodeTriggerRequest request) {
        // Stub: integrate with FFmpeg or cloud transcoder
        return Mono.just(ApiResponse.success());
    }

    public Mono<ApiResponse<Void>> scheduleAvailability(ScheduleRequest request) {
        // Stub: would persist schedule documents
        return Mono.just(ApiResponse.success());
    }
}
