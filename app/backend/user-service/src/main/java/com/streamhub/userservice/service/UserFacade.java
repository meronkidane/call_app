package com.streamhub.userservice.service;

import com.streamhub.common.api.ApiResponse;
import com.streamhub.common.domain.Profile;
import com.streamhub.common.domain.WatchHistory;
import com.streamhub.common.domain.WatchlistItem;
import com.streamhub.userservice.dto.*;
import com.streamhub.userservice.repository.ProfileRepository;
import com.streamhub.userservice.repository.WatchHistoryRepository;
import com.streamhub.userservice.repository.WatchlistRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Map;

@Service
public class UserFacade {

    private final ProfileRepository profileRepository;
    private final WatchlistRepository watchlistRepository;
    private final WatchHistoryRepository watchHistoryRepository;

    public UserFacade(ProfileRepository profileRepository,
                      WatchlistRepository watchlistRepository,
                      WatchHistoryRepository watchHistoryRepository) {
        this.profileRepository = profileRepository;
        this.watchlistRepository = watchlistRepository;
        this.watchHistoryRepository = watchHistoryRepository;
    }

    public Flux<ProfileResponse> profiles(String userId) {
        return profileRepository.findByUserId(userId)
                .map(this::toProfileResponse);
    }

    public Mono<ApiResponse<ProfileResponse>> createProfile(ProfileRequest request) {
        var profile = new Profile();
        profile.setUserId(request.userId());
        profile.setName(request.name());
        profile.setAvatar(request.avatar());
        profile.setMaturityRating(request.maturityRating());
        profile.setPreferences(Map.of());
        return profileRepository.save(profile)
                .map(saved -> ApiResponse.success(toProfileResponse(saved)));
    }

    public Mono<ApiResponse<ProfileResponse>> updateProfile(String profileId, ProfileRequest request) {
        return profileRepository.findById(profileId)
                .flatMap(existing -> {
                    existing.setName(request.name());
                    existing.setAvatar(request.avatar());
                    existing.setMaturityRating(request.maturityRating());
                    return profileRepository.save(existing);
                })
                .map(updated -> ApiResponse.success(toProfileResponse(updated)));
    }

    public Mono<ApiResponse<Void>> deleteProfile(String profileId) {
        return profileRepository.deleteById(profileId)
                .thenReturn(ApiResponse.success());
    }

    public Mono<ApiResponse<Void>> switchProfile(String userId, String profileId) {
        // Stub: actual implementation would persist active profile per device
        return profileRepository.findById(profileId)
                .filter(profile -> userId.equals(profile.getUserId()))
                .map(profile -> ApiResponse.success())
                .defaultIfEmpty(ApiResponse.error("PROFILE_NOT_FOUND", "Profile does not belong to user"));
    }

    public Flux<WatchlistResponse> watchlist(String profileId) {
        return watchlistRepository.findByProfileId(profileId)
                .map(item -> new WatchlistResponse(item.getId(), item.getContentId(), item.getAddedAt()));
    }

    public Mono<ApiResponse<Void>> addToWatchlist(WatchlistRequest request) {
        return watchlistRepository.findByProfileIdAndContentId(request.profileId(), request.contentId())
                .flatMap(existing -> Mono.just(ApiResponse.success()))
                .switchIfEmpty(Mono.defer(() -> {
                    var item = new WatchlistItem();
                    item.setProfileId(request.profileId());
                    item.setContentId(request.contentId());
                    item.setAddedAt(Instant.now());
                    return watchlistRepository.save(item).thenReturn(ApiResponse.success());
                }));
    }

    public Mono<ApiResponse<Void>> removeFromWatchlist(String profileId, String contentId) {
        return watchlistRepository.findByProfileIdAndContentId(profileId, contentId)
                .flatMap(watchlistRepository::delete)
                .thenReturn(ApiResponse.success());
    }

    public Flux<WatchHistoryResponse> history(String profileId) {
        return watchHistoryRepository.findByProfileIdOrderByUpdatedAtDesc(profileId)
                .map(record -> new WatchHistoryResponse(record.getId(), record.getContentId(), record.getPositionSec(), record.isCompleted(), record.getUpdatedAt()))
                .switchIfEmpty(Flux.empty());
    }

    public Mono<ApiResponse<WatchHistoryResponse>> updateHistory(WatchHistoryUpdateRequest request) {
        return watchHistoryRepository.findByProfileIdAndContentId(request.profileId(), request.contentId())
                .defaultIfEmpty(new WatchHistory())
                .flatMap(record -> {
                    record.setProfileId(request.profileId());
                    record.setContentId(request.contentId());
                    record.setPositionSec(request.positionSec());
                    record.setCompleted(request.completed());
                    record.setUpdatedAt(Instant.now());
                    return watchHistoryRepository.save(record);
                })
                .map(saved -> ApiResponse.success(new WatchHistoryResponse(
                        saved.getId(),
                        saved.getContentId(),
                        saved.getPositionSec(),
                        saved.isCompleted(),
                        saved.getUpdatedAt())));
    }

    private ProfileResponse toProfileResponse(Profile profile) {
        return new ProfileResponse(profile.getId(), profile.getUserId(), profile.getName(), profile.getAvatar(), profile.getMaturityRating());
    }
}
