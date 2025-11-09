package com.streamhub.authservice.controller;

import com.streamhub.authservice.dto.*;
import com.streamhub.authservice.service.AuthFacade;
import com.streamhub.common.api.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    private final AuthFacade authFacade;

    public AuthController(AuthFacade authFacade) {
        this.authFacade = authFacade;
    }

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ApiResponse<TokenResponse>> register(@Valid @RequestBody RegisterRequest request) {
        return authFacade.register(request);
    }

    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ApiResponse<TokenResponse>> login(@Valid @RequestBody LoginRequest request) {
        return authFacade.login(request);
    }

    @PostMapping(path = "/refresh", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ApiResponse<TokenResponse>> refresh(@Valid @RequestBody RefreshRequest request) {
        return authFacade.refresh(request);
    }

    @PostMapping(path = "/logout", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ApiResponse<Void>> logout(@Valid @RequestBody LogoutRequest request) {
        return authFacade.logout(request);
    }

    @GetMapping(path = "/devices/{userId}")
    public Flux<DeviceSessionResponse> devices(@PathVariable String userId) {
        return authFacade.listDevices(userId);
    }

    @PostMapping(path = "/password/reset", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ApiResponse<Void>> resetPassword(@Valid @RequestBody PasswordResetRequest request) {
        return authFacade.requestPasswordReset(request);
    }
}
