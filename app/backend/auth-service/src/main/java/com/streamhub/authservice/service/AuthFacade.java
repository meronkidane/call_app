package com.streamhub.authservice.service;

import com.streamhub.authservice.dto.*;
import com.streamhub.common.api.ApiResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

@Service
public class AuthFacade {

    public Mono<ApiResponse<TokenResponse>> register(RegisterRequest request) {
        var token = new TokenResponse("mock-access-token", 3600, "mock-refresh-token", 86400);
        return Mono.just(ApiResponse.success(token));
    }

    public Mono<ApiResponse<TokenResponse>> login(LoginRequest request) {
        var token = new TokenResponse("mock-access-token", 3600, "mock-refresh-token", 86400);
        return Mono.just(ApiResponse.success(token));
    }

    public Mono<ApiResponse<TokenResponse>> refresh(RefreshRequest request) {
        var token = new TokenResponse("mock-access-token", 3600, request.refreshToken(), 86400);
        return Mono.just(ApiResponse.success(token));
    }

    public Mono<ApiResponse<Void>> logout(LogoutRequest request) {
        return Mono.just(ApiResponse.success());
    }

    public Flux<DeviceSessionResponse> listDevices(String userId) {
        return Flux.just(new DeviceSessionResponse(UUID.randomUUID().toString(), "ANDROID_TV", Instant.now(), "ACTIVE"));
    }

    public Mono<ApiResponse<Void>> requestPasswordReset(PasswordResetRequest request) {
        return Mono.just(ApiResponse.success());
    }
}
