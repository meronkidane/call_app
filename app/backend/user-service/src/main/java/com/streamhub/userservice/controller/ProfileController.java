package com.streamhub.userservice.controller;

import com.streamhub.common.api.ApiResponse;
import com.streamhub.userservice.dto.ProfileRequest;
import com.streamhub.userservice.dto.ProfileResponse;
import com.streamhub.userservice.dto.SwitchProfileRequest;
import com.streamhub.userservice.service.UserFacade;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController {

    private final UserFacade userFacade;

    public ProfileController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping("/{userId}")
    public Flux<ProfileResponse> list(@PathVariable String userId) {
        return userFacade.profiles(userId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ApiResponse<ProfileResponse>> create(@Valid @RequestBody ProfileRequest request) {
        return userFacade.createProfile(request);
    }

    @PutMapping(path = "/{profileId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ApiResponse<ProfileResponse>> update(@PathVariable String profileId,
                                                     @Valid @RequestBody ProfileRequest request) {
        return userFacade.updateProfile(profileId, request);
    }

    @DeleteMapping("/{profileId}")
    public Mono<ApiResponse<Void>> delete(@PathVariable String profileId) {
        return userFacade.deleteProfile(profileId);
    }

    @PostMapping(path = "/{profileId}/switch", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ApiResponse<Void>> switchProfile(@PathVariable String profileId,
                                                 @Valid @RequestBody SwitchProfileRequest request) {
        return userFacade.switchProfile(request.userId(), profileId);
    }
}
